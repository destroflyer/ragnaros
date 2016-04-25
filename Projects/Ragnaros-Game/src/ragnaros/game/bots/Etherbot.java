/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.bots;

import ragnaros.core.Util;
import java.util.*;

/**
 *
 * @author Philipp
 */
public class Etherbot extends LocalGameBot
{
//    private int playerIndex;
//    private TranspositionTable table;
//
//    public Etherbot(int tableSize)
//    {
//        HashUtil.init();
//        assert tableSize <= 20;
//        table = new TranspositionTable(tableSize);
//    }
//    
//    @Override
//    public void performTurn(){
//        super.performTurn();
//        playerIndex = localGame.getCurrentPlayerIndex();
//        while(playerIndex == localGame.getCurrentPlayerIndex())
//        {
//            result = null;
//            search(0);
//            if(result == null) break;
//            executeCommand(result);
//        }
//        System.out.println(ttHits + "/" + (ttHits + ttMisses));
//        table.printNumEntries();
//    }
//    
//    private Command result = null;
//    private int ttHits = 0, ttMisses = 0;
//    private int search(int ply)
//    {
//        Player player = localGame.getPlayer(playerIndex);
//        Player enemy = localGame.getEnemy(player);
//        if(player.getLifepoints() <= 0) return -Short.MAX_VALUE;
//        if(enemy.getLifepoints() <= 0) return Short.MAX_VALUE;
//        if(playerIndex != localGame.getCurrentPlayerIndex())
//            return eval(player);
//        
//        long hash = positionHash();
//        TranspositionEntry entry = table.get(hash);
//        if(entry.hash == hash && ply != 0)
//        {
//            ttHits++;
//            return entry.score;
//        }
//        ttMisses++;
//        
//        int best = Short.MIN_VALUE;
//        LinkedList<Command> validCommands = getValidCommands();
//        assert !validCommands.isEmpty();
//        while(!validCommands.isEmpty())
//        {
//            Command command = validCommands.pop();
//            assert playerIndex == localGame.getCurrentPlayerIndex();
//            doAction(command);
//            int value = search(ply + 1);
//            undoAction();
//            assert playerIndex == localGame.getCurrentPlayerIndex(): command.toString();
//            if(best < value)
//            {
//                best = value;
//                if(ply == 0) result = command;
//            }
//        }
//        
//        assert Short.MIN_VALUE <= best && best <= Short.MAX_VALUE: best;
//        entry.hash = hash;
//        entry.score = (short)best;
//        
//        return best;
//    }
//    
//    private long positionHash()
//    {
//        Player current = localGame.getPlayer(localGame.getCurrentPlayerIndex());
//        Player opponent = current.getEnemy();
//        return (playerHash(current) * 397) ^ playerHash(opponent);
//    }
//    private long playerHash(Player player)
//    {
//        long hash = 0;
//        hash ^= HashUtil.getHandHash(player.getHand());
//        hash ^= HashUtil.getMonstersHash(player.getMonsters());
//        hash ^= HashUtil.getManaPoolHash(player.getManaPool());
//        hash ^= HashUtil.getHealthHash(player.getLifepoints());
//        return hash;
//    }
//    
//    private int eval(Player player)
//    {
//        Player enemy = localGame.getEnemy(player);
//        assert player.getLifepoints() > 0;
//        assert enemy.getLifepoints() > 0;
//        return fieldEval(player) - fieldEval(enemy);
//    }
//    
//    private int fieldEval(Player player)
//    {
//        int score = 0;
//        score += 3 * player.getLifepoints();
//        for (int i = 0; i < player.getMonsters().size(); i++)
//        {
//            MonsterCard monster = (MonsterCard) player.getMonsters().get(i);
//            score += monsterEval(monster);
//        }
//        score += 15 * player.getManaPool().size();
//        score += 10 * player.getHand().size();
//        score += player.getDeck().size();
//        return score;
//    }
//    private int monsterEval(MonsterCard monster)
//    {
//        int score = 5;
//        int atk = monster.getAttackDamage();
//        int def = monster.getCurrentLifepoints();
//        score += atk * def;
//        score += 3 * atk;
//        score += 2 * def;
//        score += monster.getMaximumLifepoints();
//        if(!monster.isTapped()) score += 1;
//        score += 5 * monster.getSpells().length;
//        return score;
//    }
//    
//    private LinkedList<Command> getValidCommands()
//    {
//        LinkedList<Command> commands = new LinkedList<Command>();
//        if(localGame.getCurrentPlayerIndex() == playerIndex)
//        {
//            Player player = localGame.getPlayer(playerIndex);
//            Hand hand = player.getHand();
//            for(int i=0;i<hand.size();i++)
//            {
//                Card card = hand.get(i);
//                /*if(card instanceof ManaProvider)
//                {
//                    if(player.canAddLand())
//                    {
//                        commands.add(new AddLandCommand(i));
//                    }
//                }
//                else if(card instanceof FieldCard)
//                {
//                    MonsterCard monsterCard = (MonsterCard) card;
//                    if(player.canPay(monsterCard.getCastCost(), monsterCard))
//                    {
//                        commands.add(new SummonMonsterCommand(i, getManaIndices(monsterCard.getCastCost().getMana()), null));
//                    }
//                }
//                else */if(card instanceof SpellCard)
//                {
//                    SpellCard spellCard = (SpellCard) card;
//                    Spell spell = spellCard.getSpell();
//                    if(player.canCast(spell))
//                    {
//                        int[] cost_manaIndices = getManaIndices(spell.getCost().getMana());
//                        LinkedList<SpellParameter[]> spellParametersList = new LinkedList<SpellParameter[]>();
//                        generateSpellParameters(spell, spellParametersList, new SpellParameter[spell.getSpellParameterFormat().size()]);
//                        for(SpellParameter[] spellParameters : spellParametersList)
//                        {
//                            commands.add(new CastSpellCommand(i, cost_manaIndices, spellParameters));
//                        }
//                    }
//                }
//            }
//            Cards monsters = player.getMonsters();
//            Cards enemyMonsters = player.getEnemy().getMonsters();
//            for(int i=0;i<monsters.size();i++)
//            {
//                MonsterCard monsterCard = (MonsterCard) monsters.get(i);
//                if(monsterCard.canAttack())
//                {
//                    if(enemyMonsters.isEmpty())
//                    {
//                        commands.add(new AttackPlayerCommand(i));
//                    }
//                    else
//                    {
//                        for(int r=0;r<enemyMonsters.size();r++)
//                        {
//                            commands.add(new AttackMonsterCommand(i, r));
//                        }
//                    }
//                }
//                for(int r=0;r<monsterCard.getSpells().length;r++)
//                {
//                    Spell spell = monsterCard.getSpells()[r];
//                    if(player.canPay(spell.getCost(), monsterCard))
//                    {
//                        int[] cost_manaIndices = getManaIndices(spell.getCost().getMana());
//                        LinkedList<SpellParameter[]> spellParametersList = new LinkedList<SpellParameter[]>();
//                        generateSpellParameters(spell, spellParametersList, new SpellParameter[spell.getSpellParameterFormat().size()]);
//                        for(SpellParameter[] spellParameters : spellParametersList)
//                        {
//                            commands.add(new CastMonsterSpellCommand(i, r, cost_manaIndices, spellParameters));
//                        }
//                    }
//                }
//            }
//            if(hand.size() > game.getGameMode().getMaximumHandCards(player))
//            {
//                for(int i=0;i<hand.size();i++)
//                {
//                    commands.add(new ThrowingOffCommand(i));
//                }
//            }
//            else
//            {
//                commands.add(new EndTurnCommand());
//            }
//        }
//        return commands;
//    }
//    
//    private void generateSpellParameters(Spell spell, LinkedList<SpellParameter[]> spellParametersList, SpellParameter[] constantParameters){
//        boolean isConstant = true;
//        for(int i=0;i<constantParameters.length;i++){
//            if(constantParameters[i] == null){
//                SpellParameterFormat_Entry entry = spell.getSpellParameterFormat().getEntry(i);
//                if((entry.getType() == SpellParameterFormat_Entry.Type.ALL) || (entry.getType() == SpellParameterFormat_Entry.Type.HAND_CARD)){
//                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(0).getHand());
//                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(1).getHand());
//                }
//                if((entry.getType() == SpellParameterFormat_Entry.Type.ALL) || (entry.getType() == SpellParameterFormat_Entry.Type.LAND)){
//                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(0).getManaPool());
//                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(1).getManaPool());
//                }
//                if((entry.getType() == SpellParameterFormat_Entry.Type.ALL) || (entry.getType() == SpellParameterFormat_Entry.Type.MONSTER)){
//                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(0).getMonsters());
//                    generateSpellParameter(spell, spellParametersList, constantParameters, i, localGame.getPlayer(1).getMonsters());
//                }
//                isConstant = false;
//            }
//        }
//        if(isConstant){
//            spellParametersList.add(constantParameters);
//        }
//    }
//    
//    private void generateSpellParameter(Spell spell, LinkedList<SpellParameter[]> spellParametersList, SpellParameter[] constantParameters, int parameterIndex, Cards targetCards){
//        SpellParameterFormat_Entry entry = spell.getSpellParameterFormat().getEntry(parameterIndex);
//        for(int i=0;i<targetCards.size();i++){
//            Card targetCard = targetCards.get(i);
//            if((spell.getCaster() instanceof SpellCard) && (targetCard == spell.getCaster())){
//                continue;
//            }
//            if((entry.getTargetOwner() == SpellParameterFormat_Entry.Owner.ALL)
//            || ((entry.getTargetOwner() == SpellParameterFormat_Entry.Owner.ALLIED) && (targetCard.getOwner() == spell.getCaster().getOwner()))
//            || ((entry.getTargetOwner() == SpellParameterFormat_Entry.Owner.ENEMY) && (targetCard.getOwner() != spell.getCaster().getOwner()))){
//                SpellParameter[] spellParameters = new SpellParameter[constantParameters.length];
//                for(int r=0;r<parameterIndex;r++){
//                    spellParameters[r] = constantParameters[r];
//                }
//                SpellParameter spellParameter;
//                if(targetCards instanceof Hand){
//                    spellParameter = new HandCardParameter(targetCard.getOwner().getIndex(), i);
//                }
//                else if(targetCards instanceof ManaPool){
//                    spellParameter = new LandParameter(targetCard.getOwner().getIndex(), i);
//                }
//                else{
//                    spellParameter = new MonsterParameter(targetCard.getOwner().getIndex(), i);
//                }
//                spellParameters[parameterIndex] = spellParameter;
//                generateSpellParameters(spell, spellParametersList, spellParameters);
//            }
//        }
//    }
//
//    private int[] getManaIndices(ManaAmount manaAmount){
//        LinkedList<Integer> manaIndices = new LinkedList<Integer>();
//        ManaPool manaPool = localGame.getPlayer(localGame.getCurrentPlayerIndex()).getManaPool();
//        for(Mana mana : Mana.values()){
//            if(mana != Mana.CUSTOM){
//                int remainingAmount = manaAmount.getMana(mana);
//                for(int i=0;i<manaPool.size();i++){
//                    ManaProvider manaProvider = (ManaProvider) manaPool.get(i);
//                    if(manaProvider.getProvidedMana().getMana(mana) == 1){
//                        if(!manaIndices.contains(i)){
//                            manaIndices.add(i);
//                            remainingAmount--;
//                            if(remainingAmount <= 0){
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        int remainingCustomAmount = manaAmount.getMana(Mana.CUSTOM);
//        for(int i=0;i<manaPool.size();i++){
//            ManaProvider manaProvider = (ManaProvider) manaPool.get(i);
//            int providedAmount = manaProvider.getProvidedMana().getAmount();
//            if((!manaIndices.contains(i)) && (providedAmount <= remainingCustomAmount)){
//                manaIndices.add(i);
//                remainingCustomAmount -= providedAmount;
//                if(remainingCustomAmount <= 0){
//                    break;
//                }
//            }
//        }
//        return Util.convertToArray(manaIndices);
//    }
//    
}
