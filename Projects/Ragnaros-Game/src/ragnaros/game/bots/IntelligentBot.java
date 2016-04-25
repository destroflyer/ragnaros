/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.bots;

import java.util.LinkedList;
import ragnaros.game.*;
import ragnaros.game.commands.*;
import ragnaros.game.lands.*;
import ragnaros.game.monsters.spells.parameter.*;

/**
 *
 * @author Carl
 */
public class IntelligentBot extends LocalGameBot{
    
    private int playerIndex;
    
    @Override
    public void performTurn(){
        super.performTurn();
        playerIndex = localGame.getCurrentPlayerIndex();
        castAllBasicMana();
        LinkedList<Command> bestCommands = getBestCommands(new LinkedList<Command>(), 0);
        for(Command command : bestCommands){
            executeCommand(command);
        }
    }
    
    private void castAllBasicMana(){
        Cards lands = localGame.getPlayer(playerIndex).getLands();
        for(int i=0;i<lands.size();i++){
            Card landCard = lands.get(i);
            if(landCard instanceof BasicManaLand){
                executeCommand(new CastFieldCardSpellCommand(new CardPosition(CardPosition.Zone.LAND, i), 0, null));
            }
        }
    }
    
    private LinkedList<Command> getBestCommands(LinkedList<Command> previousCommands, int depth){
        LinkedList<Command> validCommands = getValidCommands();
        LinkedList<Command> bestCommands = previousCommands;
        int bestCommandsEvaluation = Integer.MIN_VALUE;
        while(!validCommands.isEmpty()){
            Command command = validCommands.pop();
            doAction(command);
            LinkedList<Command> newPreviousCommands = new LinkedList<Command>(previousCommands);
            newPreviousCommands.add(command);
            LinkedList<Command> commands = getBestCommands(newPreviousCommands, depth + 1);
            int evaluation = getGameEvaluation();
            if(evaluation > bestCommandsEvaluation){
                bestCommands = commands;
                bestCommandsEvaluation = evaluation;
            }
            undoAction();
        }
        return bestCommands;
    }
    
    private LinkedList<Command> getValidCommands(){
        LinkedList<Command> commands = new LinkedList<Command>();
        if(localGame.getCurrentPlayerIndex() == playerIndex){
            Player player = localGame.getPlayer(playerIndex);
            Hand hand = player.getHand();
            for(int i=0;i<hand.size();i++){
                Card card = hand.get(i);
                if(card instanceof FieldCard){
                    FieldCard fieldCard = (FieldCard) card;
                    if(player.canPay(fieldCard.getCastCost(), fieldCard) && ((!(fieldCard instanceof Land)) || player.canAddLand())){
                        LinkedList<SpellParameter[]> spellParametersList = new LinkedList<SpellParameter[]>();
                        if(fieldCard.getCastSpell() != null){
                            generateSpellParameters(fieldCard.getCastSpell(), spellParametersList, new SpellParameter[fieldCard.getCastSpell().getSpellParameterFormat().size()]);
                        }
                        else{
                            spellParametersList.add(null);
                        }
                        for(SpellParameter[] spellParameters : spellParametersList){
                            commands.add(new CastFieldCardCommand(i, spellParameters));
                        }
                    }
                }
                else if(card instanceof SpellCard){
                    SpellCard spellCard = (SpellCard) card;
                    Spell spell = spellCard.getSpell();
                    if(player.canCast(spell)){
                        LinkedList<SpellParameter[]> spellParametersList = new LinkedList<SpellParameter[]>();
                        generateSpellParameters(spell, spellParametersList, new SpellParameter[spell.getSpellParameterFormat().size()]);
                        for(SpellParameter[] spellParameters : spellParametersList){
                            commands.add(new CastSpellCommand(i, spellParameters));
                        }
                    }
                }
            }
            Cards monsters = player.getMonsters();
            Cards enemyMonsters = player.getEnemy().getMonsters();
            addValidCommands_FieldCardsSpells(commands, player, player.getLands());
            addValidCommands_FieldCardsSpells(commands, player, monsters);
            for(int i=0;i<monsters.size();i++){
                MonsterCard monsterCard = (MonsterCard) monsters.get(i);
                if(enemyMonsters.isEmpty()){
                    if(monsterCard.canAttack()){
                        commands.add(new AttackPlayerCommand(i));
                    }
                }
                else{
                    for(int r=0;r<enemyMonsters.size();r++){
                        MonsterCard enemyMonsterCard = (MonsterCard) enemyMonsters.get(r);
                        if(monsterCard.canAttack(enemyMonsterCard)){
                            commands.add(new AttackMonsterCommand(i, r));
                        }
                    }
                }
            }
            if(hand.size() > game.getGameMode().getMaximumHandCards(player)){
                for(int i=0;i<hand.size();i++){
                    commands.add(new ThrowingOffCommand(i));
                }
            }
            else{
                commands.add(new EndTurnCommand());
            }
        }
        return commands;
    }
    
    private void addValidCommands_FieldCardsSpells(LinkedList<Command> commands, Player player, Cards fieldCards){
        for(int i=0;i<fieldCards.size();i++){
            FieldCard fieldCard = (FieldCard) fieldCards.get(i);
            for(int r=0;r<fieldCard.getSpells().length;r++){
                Spell spell = fieldCard.getSpells()[r];
                if(player.canPay(spell.getCost(), fieldCard)){
                    LinkedList<SpellParameter[]> spellParametersList = new LinkedList<SpellParameter[]>();
                    generateSpellParameters(spell, spellParametersList, new SpellParameter[spell.getSpellParameterFormat().size()]);
                    for(SpellParameter[] spellParameters : spellParametersList){
                        commands.add(new CastFieldCardSpellCommand(fieldCard.getCardPosition(), r, spellParameters));
                    }
                }
            }
        }
    }
    
    private void generateSpellParameters(Spell spell, LinkedList<SpellParameter[]> spellParametersList, SpellParameter[] constantParameters){
        boolean isConstant = true;
        for(int i=0;i<constantParameters.length;i++){
            if(constantParameters[i] == null){
                SpellParameterFormat_Entry entry = spell.getSpellParameterFormat().getEntry(i);
                if((entry.getType() == SpellParameterFormat_Entry.Type.ALL) || (entry.getType() == SpellParameterFormat_Entry.Type.HAND_CARD)){
                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(0).getHand());
                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(1).getHand());
                }
                if((entry.getType() == SpellParameterFormat_Entry.Type.ALL) || (entry.getType() == SpellParameterFormat_Entry.Type.LAND)){
                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(0).getLands());
                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(1).getLands());
                }
                if((entry.getType() == SpellParameterFormat_Entry.Type.ALL) || (entry.getType() == SpellParameterFormat_Entry.Type.MONSTER)){
                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(0).getMonsters());
                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(1).getMonsters());
                }
                isConstant = false;
            }
        }
        if(isConstant){
            spellParametersList.add(constantParameters);
        }
    }
    
    private void generateSpellParameter(Spell spell, LinkedList<SpellParameter[]> spellParametersList, SpellParameter[] constantParameters, int parameterIndex, Cards targetCards){
        SpellParameterFormat_Entry entry = spell.getSpellParameterFormat().getEntry(parameterIndex);
        for(int i=0;i<targetCards.size();i++){
            Card targetCard = targetCards.get(i);
            if((spell.getCaster() instanceof SpellCard) && (targetCard == spell.getCaster())){
                continue;
            }
            if((entry.getTargetOwner() == SpellParameterFormat_Entry.Owner.ALL)
            || ((entry.getTargetOwner() == SpellParameterFormat_Entry.Owner.ALLIED) && (targetCard.getOwner() == spell.getCaster().getOwner()))
            || ((entry.getTargetOwner() == SpellParameterFormat_Entry.Owner.ENEMY) && (targetCard.getOwner() != spell.getCaster().getOwner()))){
                SpellParameter[] spellParameters = new SpellParameter[constantParameters.length];
                for(int r=0;r<parameterIndex;r++){
                    spellParameters[r] = constantParameters[r];
                }
                SpellParameter spellParameter = null;
                switch(targetCards.getZone()){
                    case HAND:
                        spellParameter = new HandCardParameter(targetCard.getOwner().getIndex(), i);
                        break;
                    
                    case LAND:
                        spellParameter = new LandParameter(targetCard.getOwner().getIndex(), i);
                        break;
                    
                    case MONSTER:
                        spellParameter = new MonsterParameter(targetCard.getOwner().getIndex(), i);
                        break;
                }
                spellParameters[parameterIndex] = spellParameter;
                generateSpellParameters(spell, spellParametersList, spellParameters);
            }
        }
    }
    
    private int getGameEvaluation(){
        Player player = localGame.getPlayer(playerIndex);
        if(player.getEnemy().getLifepoints() <= 0){
            return Integer.MAX_VALUE;
        }
        int evaluation = 0;
        evaluation += player.getLands().size();
        evaluation += getFieldEvaluation(player);
        evaluation -= getFieldEvaluation(player.getEnemy());
        evaluation /= player.getEnemy().getLifepoints();
        return evaluation;
    }
    
    private int getFieldEvaluation(Player player){
        int evaluation = 0;
        evaluation += player.getLands().size();
        for(int i=0;i<player.getMonsters().size();i++){
            MonsterCard monsterCard = (MonsterCard) player.getMonsters().get(i);
            evaluation += (monsterCard.getAttackDamage() + monsterCard.getCurrentLifepoints());
        }
        return evaluation;
    }
}
