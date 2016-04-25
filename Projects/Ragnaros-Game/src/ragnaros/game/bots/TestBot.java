/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game.bots;

import java.util.LinkedList;
import ragnaros.core.Util;

/**
 *
 * @author Carl
 */
public class TestBot {//extends LocalGameBot{
//    
//    @Override
//    public void performTurn(){
//        super.performTurn();
//        Player player = localGame.getCurrentPlayer();
//        Hand hand = player.getHand();
//        for(int i=0;i<hand.size();i++){
//            Card card = hand.get(i);
//            /*if(card instanceof ManaProvider){
//                if(player.canAddLand()){
//                    executeCommand(new AddLandCommand(i));
//                }
//            }
//            else if(card instanceof MonsterCard){
//                MonsterCard monsterCard = (MonsterCard) card;
//                if(player.canPay(monsterCard.getCastCost(), monsterCard)){
//                    executeCommand(new SummonMonsterCommand(i, getManaIndices(monsterCard.getCastCost().getMana()), null));
//                }
//            }*/
//        }
//        Cards monsters = player.getMonsters();
//        for(int i=0;i<monsters.size();i++){
//            MonsterCard monsterCard = (MonsterCard) monsters.get(i);
//            if(monsterCard instanceof Soraka){
//                int spellIndex = 0;
//                Spell spell = monsterCard.getSpells()[spellIndex];
//                if(player.canPay(spell.getCost(), monsterCard)){
//                    executeCommand(new CastMonsterSpellCommand(i, spellIndex, getManaIndices(spell.getCost().getMana()), new SpellParameter[0]));
//                }
//            }
//            else if(monsterCard.canAttack()){
//                if(player.getEnemy().getMonsters().isEmpty()){
//                    executeCommand(new AttackPlayerCommand(i));
//                }
//                else{
//                    executeCommand(new AttackMonsterCommand(i, 0));
//                }
//            }
//        }
//        boolean hadToDiscard = false;
//        while(hand.size() > game.getGameMode().getMaximumHandCards(player)){
//            executeCommand(new ThrowingOffCommand(0));
//            hadToDiscard = true;
//        }
//        if(!hadToDiscard){
//            executeCommand(new EndTurnCommand());
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
}
