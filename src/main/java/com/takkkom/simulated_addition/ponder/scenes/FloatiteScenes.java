package com.takkkom.simulated_addition.ponder.scenes;

import com.simibubi.create.content.redstone.analogLever.AnalogLeverBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import dev.simulated_team.simulated.content.blocks.analog_transmission.AnalogTransmissionBlockEntity;
import dev.simulated_team.simulated.content.blocks.portable_engine.PortableEngineBlockEntity;
import dev.simulated_team.simulated.ponder.SmoothMovementUtils;
import dev.simulated_team.simulated.ponder.instructions.CustomAnimateWorldSectionInstruction;
import dev.simulated_team.simulated.ponder.instructions.PullTheAssemblerKronkInstruction;
import dev.simulated_team.simulated.service.SimItemService;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.api.scene.SelectionUtil;
import net.createmod.ponder.foundation.instruction.FadeOutOfSceneInstruction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class FloatiteScenes {
    public static void strongBuoyancy(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final SelectionUtil select = util.select();

        scene.title("very_strong_buoyancy", "Very strong buoyancy");
        scene.configureBasePlate(0, 0, 15);
        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.scaleSceneView(0.7f);
        scene.setSceneOffsetY(0);

        final ElementLink<WorldSectionElement> structureIntroCore = scene.world().showIndependentSection(select.fromTo(5, 1, 5, 9, 6, 9), Direction.DOWN);

        scene.idle(6);

        final ElementLink<WorldSectionElement> structureIntroFrame1 = scene.world().showIndependentSection(select.fromTo(5, 1, 4, 9, 1, 4), Direction.DOWN);
        scene.idle(3);
        final ElementLink<WorldSectionElement> structureIntroFrame2 = scene.world().showIndependentSection(select.fromTo(4, 1, 5, 4, 1, 9), Direction.DOWN);
        scene.idle(3);
        final ElementLink<WorldSectionElement> structureIntroFrame3 = scene.world().showIndependentSection(select.fromTo(5, 1, 10, 9, 1, 10), Direction.DOWN);
        scene.idle(3);
        final ElementLink<WorldSectionElement> structureIntroFrame4 = scene.world().showIndependentSection(select.fromTo(10, 1, 5, 10, 1, 10), Direction.DOWN);
        scene.idle(3);

        final ElementLink<WorldSectionElement> structureIntroFloatite1 = scene.world().showIndependentSection(select.fromTo(5, 1, 2, 9, 2, 3), Direction.DOWN);
        scene.idle(3);
        final ElementLink<WorldSectionElement> structureIntroFloatite2 = scene.world().showIndependentSection(select.fromTo(2, 1, 5, 3, 2, 9), Direction.DOWN);
        scene.idle(3);
        final ElementLink<WorldSectionElement> structureIntroFloatite3 = scene.world().showIndependentSection(select.fromTo(5, 1, 11, 9, 2, 12), Direction.DOWN);
        scene.idle(3);
        final ElementLink<WorldSectionElement> structureIntroFloatite4 = scene.world().showIndependentSection(select.fromTo(11, 1, 5, 12, 2, 9), Direction.DOWN);
        scene.idle(20);

        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroCore));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFrame1));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFrame2));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFrame3));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFrame4));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFloatite1));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFloatite2));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFloatite3));
        scene.addInstruction(new FadeOutOfSceneInstruction<>(0, null, structureIntroFloatite4));

        final Selection structure = select.fromTo(2, 1, 2, 12, 6, 12);
        final BlockPos assembler = new BlockPos(7, 6, 7);
        final ElementLink<WorldSectionElement> assembledStructure = scene.world().showIndependentSectionImmediately(structure);

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Floatite has very strong buoyancy and is lightweight compared to other blocks.")
                .pointAt(util.vector().of(7, 2, 2));

        scene.idle(80);

        scene.addKeyframe();

        scene.overlay().showControls(util.vector().centerOf(assembler), Pointing.DOWN, 20).rightClick();
        scene.idle(3);
        scene.addInstruction(new PullTheAssemblerKronkInstruction(assembler, true, false));
        scene.idle(20);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(0, -3, 0), 30, SmoothMovementUtils.quadraticRiseInOut()));
        scene.idle(30);
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(0, 1, 0), 30, SmoothMovementUtils.quadraticRiseInOut()));
        scene.idle(30);

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Therefore, even with only a small number of floatites attached, it is possible to make very heavy contraptions float on water.")
                .pointAt(util.vector().centerOf(0, 1, 0));

        scene.idle(80);
        scene.markAsFinished();
    }

    public static void lowFriction(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final SelectionUtil select = util.select();

        scene.title("low_friction", "Low Friction");
        scene.configureBasePlate(0, 0, 15);
        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.scaleSceneView(0.8f);
        scene.setSceneOffsetY(0);

        scene.idle(5);

        final Selection structure = select.fromTo(1, 1, 3, 8, 4, 11);
        final BlockPos assembler = new BlockPos(3, 3, 7);
        final ElementLink<WorldSectionElement> assembledStructure = scene.world().showIndependentSection(structure, Direction.DOWN);

        BlockPos enginePos = util.grid().at(4, 3, 7);
        scene.world().modifyBlockEntity(enginePos, PortableEngineBlockEntity.class, be -> {
            be.setCurrentBurnTime(SimItemService.INSTANCE.getBurnTime(Items.COAL.getDefaultInstance()));
        });

        ArrayList<BlockPos> engineKinetics = new ArrayList<>();
        engineKinetics.add(enginePos);
        engineKinetics.add(util.grid().at(5, 3, 7));

        engineKinetics.forEach(engineKineticPos -> {
            Selection kinetic = util.select().position(engineKineticPos);
            scene.world().setKineticSpeed(kinetic, 32);
        });
        BlockPos transmissionPos = util.grid().at(5, 2, 7);
        scene.world().modifyBlockEntityNBT(select.position(transmissionPos), AnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", -32);
        });

        scene.idle(20);

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Floatite is lighter and has no lateral friction compared to Levitite.")
                .pointAt(util.vector().of(5, 1, 6));

        scene.idle(80);

        scene.overlay().showControls(util.vector().centerOf(assembler), Pointing.DOWN, 20).rightClick();
        scene.idle(3);

        scene.addInstruction(new PullTheAssemblerKronkInstruction(assembler, true, true));
        scene.idle(10);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(0, -1, 0), 30, SmoothMovementUtils.quadraticRiseInOut()));
        scene.idle(30);
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(0, 0.5, 0), 30, SmoothMovementUtils.quadraticRiseInOut()));
        scene.idle(30);

        scene.addKeyframe();

        BlockPos lever = new BlockPos(6, 3, 7);
        scene.world().modifyBlockEntityNBT(select.position(lever), AnalogLeverBlockEntity.class, tag -> tag.putInt("State", 13));

        BlockPos propellerPos = util.grid().at(8, 2, 7);
        Selection propeller = util.select().position(propellerPos);
        scene.world().setKineticSpeed(propeller, 256);

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Therefore, it is extremely useful for contraptions that require high-speed movement on the water, such as with seaplanes and high-speed boards.");

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(40, 2, 0), 90, SmoothMovementUtils.quadraticRise()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(assembledStructure, new Vec3(0, 0, 10), 60, SmoothMovementUtils.quadraticRiseInOut()));

        scene.idle(80);

        scene.markAsFinished();
    }
}
