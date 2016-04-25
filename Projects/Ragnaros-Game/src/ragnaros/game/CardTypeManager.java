/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ragnaros.game;

import java.util.HashMap;
import ragnaros.core.Util;
import ragnaros.game.*;
import ragnaros.game.lands.*;
import ragnaros.game.monsters.*;
import ragnaros.game.spellcards.*;

/**
 *
 * @author Carl
 */
public class CardTypeManager{
    
    private static HashMap<Class<? extends Card>, Integer> types = new HashMap<Class<? extends Card>, Integer>();
    private static int nextCardType;
    static{
        registerTypes(
            Plains.class,
            Mountain.class,
            Forest.class,
            Island.class,
            Swamp.class,
            Orianna.class,
            Urgot.class,
            Brand.class,
            Shyvana.class,
            Yorick.class,
            Ghoul.class,
            Darius.class,
            Maokai.class,
            Garen.class,
            ManEaterBug.class,
            Soraka.class,
            Wukong.class,
            Zilean.class,
            Tryndamere.class,
            Smite.class,
            Ignite.class,
            RubyCristal.class,
            LeBlanc.class,
            LeBlancClone.class,
            Katarina.class,
            Ghost.class,
            Kappa.class,
            Gerd.class,
            Dogs.class,
            Dog1.class,
            Dog2.class,
            Dog3.class,
            Dog4.class,
            Dog5.class,
            LittleRed.class,
            Nami.class,
            SwordComboGirl.class,
            Taric.class,
            AraHaan.class,
            CardDestruction.class,
            Fish.class,
            Konda.class,
            LightningStrike.class,
            PotOfGreed.class,
            Smaug.class,
            AngelOfMercy.class,
            Ashe.class,
            Annie.class,
            Tibbers.class,
            Mordekaiser.class,
            Ziggs.class,
            Zombie.class,
            UnderworldDreams.class,
            Assasinate.class,
            PrematureBurial.class,
            MurlocTidehunter.class,
            MurlocScout.class,
            RagnarosTheFirelord.class,
            WaterDrake.class,
            Shyvonna.class,
            ForestOgre.class,
            Charmander.class,
            Charmeleon.class,
            Charizard.class,
            Magikarp.class,
            Gyarados.class,
            Lapras.class,
            Pinsir.class,
            Safcon.class,
            Tauros.class,
            RoyMustang.class,
            Ditto.class,
            GoblinRockSled.class,
            AncientTemple.class,
            ArcaneSacrifice.class,
            Chansey.class,
            ElvishGirl.class,
            Renewal.class,
            SunPriest.class,
            SwordsOfRevealingLight.class,
            TolVir.class,
            ImpMaster.class,
            Imp.class,
            LeperGnome.class,
            IllidanStormrage.class,
            FlameOfAzzinoth.class,
            KappaMeteor.class,
            Klappa.class,
            Sparks.class,
            Ookazi.class,
            ThunderCrash.class,
            InjuredBlademaster.class,
            CircleOfHealing.class,
            AuchenaiSoulpriest.class,
            LesserHeal.class,
            Brian.class,
            GahzRilla.class,
            Houndmaster.class,
            Puppy.class,
            StarvingBuzzard.class,
            Bicycle.class,
            Doghouse.class,
            KillCommand.class,
            NurseJoy.class,
            PokePotion.class,
            Pokeball.class,
            ProfessorOak.class,
            SummonPuppies.class,
            Missingno.class,
            SavannahHighmane.class,
            Hyena.class,
            RareCandy.class,
            Deathwing.class,
            Leokk.class,
            RaidLeader.class,
            DireWolfAlpha.class,
            Ekans.class,
            Arbok.class,
            YoshiBeam.class,
            Slurpy.class,
            Confuse.class,
            Aaah.class,
            FireDwarf.class,
            Fireblast.class,
            EruptingVolcano.class,
            WildOcean.class,
            Tuska.class,
            AcidicSwamp.class,
            BurnedOutCity.class,
            BurningWoods.class,
            CloudySky.class,
            Fountain.class,
            HuntingGrounds.class,
            OilFlames.class,
            OvergrownTemple.class,
            PollutedRiver.class,
            WheatFields.class,
            SnowLake.class,
            BonesLocality.class,
            Seagrass.class,
            Steam.class,
            RedSky.class,
            Glade.class,
            AbandonedCabin.class,
            GrizzlyBears.class,
            ElvishWarrior.class,
            TrainedArmodon.class,
            LlanowarBehemoth.class,
            VerdantForce.class,
            Saproling.class,
            BlanchwoodArmor.class,
            VoyagingSatyr.class,
            FlameRift.class,
            GoblinGuide.class,
            MonasterySwiftspear.class,
            EidolonOfTheGreatRevel.class,
            HellsparkElemental.class,
            GrimLavamancer.class,
            ArgentSquire.class,
            ShieldedMinibot.class,
            Mirth.class,
            GallowsWarden.class,
            PillarfieldOx.class,
            GoldshireFootman.class,
            MogushanWarden.class,
            SenjinShieldmasta.class,
            SunfuryProtector.class,
            Sunwalker.class,
            CryptRipper.class,
            GuulDrazVampire.class,
            LilianasSpecter.class,
            PhyrexianRager.class,
            GraspOfDarkness.class,
            LastKiss.class,
            WallOfFrost.class,
            VedalkenInfuser.class,
            SphinxOfMagosi.class,
            AetherAdept.class,
            WaterServant.class,
            TempestOwl.class,
            Tyranitar.class,
            CrownedCeratok.class
        );
    }
    
    private static void registerTypes(Class<? extends Card>... cardClasses){
        for(Class<? extends Card> cardClass : cardClasses){
            types.put(cardClass, nextCardType);
            nextCardType++;
        }
    }
    
    public static Card createCard(int type){
        return createCard(Util.getHashKeyByValue(types, type));
    }
    
    public static Card createCard(Class<? extends Card> cardClass){
        return Util.createObjectByClass(cardClass);
    }
    
    public static int getType(Card card){
        return types.get(card.getClass());
    }

    public static HashMap<Class<? extends Card>, Integer> getTypes(){
        return types;
    }
}
