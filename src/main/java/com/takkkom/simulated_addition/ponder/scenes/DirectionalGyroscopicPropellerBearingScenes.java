package com.takkkom.simulated_addition.ponder.scenes;

import com.simibubi.create.content.redstone.analogLever.AnalogLeverBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import dev.eriksonn.aeronautics.content.ponder.instructions.ChangePropellerRotateInstruction;
import dev.eriksonn.aeronautics.content.ponder.instructions.PropellerRotateInstruction;
import dev.simulated_team.simulated.content.blocks.analog_transmission.AnalogTransmissionBlockEntity;
import dev.simulated_team.simulated.content.blocks.portable_engine.PortableEngineBlockEntity;
import dev.simulated_team.simulated.ponder.SmoothMovementUtils;
import dev.simulated_team.simulated.ponder.instructions.CustomAnimateWorldSectionInstruction;
import dev.simulated_team.simulated.ponder.instructions.PullTheAssemblerKronkInstruction;
import dev.simulated_team.simulated.service.SimItemService;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.api.scene.SelectionUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.phys.Vec3;
import oshi.util.tuples.Pair;

import java.util.ArrayList;

public class DirectionalGyroscopicPropellerBearingScenes {
    public static void thrustVectoring(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final SelectionUtil select = util.select();

        scene.title("thrust_vectoring_for_helicopter", "Thrust Vectoring for Helicopter");
        scene.configureBasePlate(0, 0, 9);
        scene.scaleSceneView(0.8f);
        scene.setSceneOffsetY(-5);

        scene.showBasePlate();


        final Selection structure = select.fromTo(0, 1, 0, 8, 6, 8);
        final BlockPos assembler = new BlockPos(4, 2, 3);
        final ElementLink<WorldSectionElement> assembledStructure = scene.world().showIndependentSectionImmediately(structure);

        BlockPos propellerBearingPos = new BlockPos(4, 6, 4);
        BlockPos propellerSailsPos = new BlockPos(4, 7, 4);
        final Selection propellerSailsStructure = select.fromTo(0, 7, 0, 8, 7, 8);
        final ElementLink<WorldSectionElement> propellerSails = scene.world().showIndependentSectionImmediately(propellerSailsStructure);
        scene.world().configureCenterOfRotation(propellerSails, Vec3.atCenterOf(propellerSailsPos));

        BlockPos enginePos = util.grid().at(4, 2, 5);
        scene.world().modifyBlockEntity(enginePos, PortableEngineBlockEntity.class, be -> {
            be.setCurrentBurnTime(SimItemService.INSTANCE.getBurnTime(Items.COAL.getDefaultInstance()));
        });

        ArrayList<Pair<Integer, BlockPos>> engineKinetics = new ArrayList<>();
        engineKinetics.add(new Pair<>(1, enginePos));
        engineKinetics.add(new Pair<>(1, new BlockPos(4, 2, 4)));
        engineKinetics.add(new Pair<>(1, new BlockPos(4, 3, 4)));
        engineKinetics.add(new Pair<>(1, new BlockPos(4, 4, 4)));
        engineKinetics.add(new Pair<>(1, new BlockPos(4, 5, 4)));
        int engineSpeed = -32;

        engineKinetics.forEach(engineKineticPos -> {
            Selection kinetic = util.select().position(engineKineticPos.getB());
            scene.world().setKineticSpeed(kinetic, engineSpeed * engineKineticPos.getA());
        });

        scene.addInstruction(new PullTheAssemblerKronkInstruction(assembler, true, true));




        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(0, 2, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0, 2, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        PropellerRotateInstruction propellerRotation = new PropellerRotateInstruction(propellerBearingPos, propellerSails, Direction.DOWN, engineSpeed,4);
        scene.addInstruction(propellerRotation);
        scene.addInstruction(new ChangePropellerRotateInstruction.SetParticles(propellerRotation, propellerSailsPos,null,5f,-5,4f,false));




        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Directional Gyroscopic Propeller Bearings stabilize contraptions while tilting thrust in the direction of the redstone signal.");

        scene.idle(100);
        scene.addKeyframe();


        scene.overlay().showText(100)
                .placeNearTarget()
                .text("Therefore, it is useful for vehicles such as helicopters.");

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(-0.15, 0, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, 10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(-1, 0, 0), 20, SmoothMovementUtils.quadraticRise()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(-1, 0, 0), 20, SmoothMovementUtils.quadraticRise()));
        scene.world().modifyBlock(new BlockPos(3, 6, 4), s -> s.setValue(RedStoneWireBlock.POWER, 15), false);

        scene.idle(20);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0.15, 0, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, -10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(-1, 0, 0), 20, SmoothMovementUtils.quadraticRiseOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(-1, 0, 0), 20, SmoothMovementUtils.quadraticRiseOut()));
        scene.world().modifyBlock(new BlockPos(3, 6, 4), s -> s.setValue(RedStoneWireBlock.POWER, 0), false);

        scene.idle(20);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0.15, 0, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, -10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(1, 0, 0), 20, SmoothMovementUtils.quadraticRise()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(1, 0, 0), 20, SmoothMovementUtils.quadraticRise()));
        scene.world().modifyBlock(new BlockPos(5, 6, 4), s -> s.setValue(RedStoneWireBlock.POWER, 15), false);

        scene.idle(20);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(-0.15, 0, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, 10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(1, 0, 0), 20, SmoothMovementUtils.quadraticRiseOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(1, 0, 0), 20, SmoothMovementUtils.quadraticRiseOut()));
        scene.world().modifyBlock(new BlockPos(5, 6, 4), s -> s.setValue(RedStoneWireBlock.POWER, 0), false);

        scene.idle(20);

        scene.markAsFinished();
    }

    public static void suppressThrustLeakage(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final SelectionUtil select = util.select();

        scene.title("suppress_thrust_leakage", "Suppress thrust leakage");
        scene.configureBasePlate(0, 0, 9);
        scene.scaleSceneView(0.8f);
        scene.setSceneOffsetY(-5);

        scene.showBasePlate();



        final Selection structure = select.fromTo(0, 2, 0, 8, 9, 8);
        final BlockPos assembler = new BlockPos(4, 4, 2);
        final ElementLink<WorldSectionElement> assembledStructure = scene.world().showIndependentSectionImmediately(structure);

        final Selection propellerSailsStructure = select.fromTo(0, 1, 0, 8, 1, 8);
        final ElementLink<WorldSectionElement> propellerSails = scene.world().showIndependentSectionImmediately(propellerSailsStructure);

        ArrayList<Pair<Integer, BlockPos>> engineKinetics = new ArrayList<>();
        engineKinetics.add(new Pair<>(1, util.grid().at(2, 5, 2)));
        engineKinetics.add(new Pair<>(1, util.grid().at(1, 5, 2)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(1, 4, 2)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(1, 4, 3)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(2, 4, 3)));
        engineKinetics.add(new Pair<>(1, util.grid().at(3, 4, 3)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(3, 3, 3)));
        engineKinetics.add(new Pair<>(1, util.grid().at(4, 3, 3)));
        engineKinetics.add(new Pair<>(1, util.grid().at(4, 4, 3)));

        engineKinetics.add(new Pair<>(1, util.grid().at(3, 5, 2)));
        engineKinetics.add(new Pair<>(1, util.grid().at(4, 5, 2)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(4, 6, 2)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(3, 6, 2)));
        engineKinetics.add(new Pair<>(1, util.grid().at(3, 6, 3)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(3, 6, 4)));
        engineKinetics.add(new Pair<>(1, util.grid().at(3, 6, 5)));
        engineKinetics.add(new Pair<>(-1, util.grid().at(3, 7, 5)));

        int engineSpeed = 64;

        BlockPos transmissionPos = util.grid().at(4, 4, 4);
        scene.world().modifyBlockEntityNBT(select.position(transmissionPos), AnalogTransmissionBlockEntity.class, nbt -> {
            nbt.getCompound("ExtraCogwheel").putFloat("Speed", -engineSpeed);
        });

        engineKinetics.forEach(engineKineticPos -> {
            Selection kinetic = util.select().position(engineKineticPos.getB());
            scene.world().setKineticSpeed(kinetic, engineSpeed * engineKineticPos.getA());
        });

        int transmissionSpeed = -64;
        scene.world().setKineticSpeed(util.select().position(transmissionPos), transmissionSpeed);
        scene.world().setKineticSpeed(util.select().position(4, 3, 4), transmissionSpeed);
        scene.world().setKineticSpeed(util.select().position(4, 2, 4), transmissionSpeed);

        scene.addInstruction(new PullTheAssemblerKronkInstruction(assembler, true, true));


        BlockPos propellerBearingPos = new BlockPos(4, 2, 4);
        BlockPos propellerSailsPos = new BlockPos(4, 1, 4);
        scene.world().configureCenterOfRotation(propellerSails, Vec3.atCenterOf(propellerSailsPos));

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(0, 2, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0, 2, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        PropellerRotateInstruction propellerRotation = new PropellerRotateInstruction(propellerBearingPos, propellerSails, Direction.DOWN,transmissionSpeed,4);
        scene.addInstruction(propellerRotation);
        scene.addInstruction(new ChangePropellerRotateInstruction.SetParticles(propellerRotation, propellerSailsPos,null,5f,-5,4f,false));


        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Gyroscopic Propeller Bearings and Directional Gyroscopic Propeller Bearings can stabilize contraptions with a high center of gravity when mounted facing downwards, but...");


        scene.idle(100);

        scene.addKeyframe();

        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, 10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0.25, 0, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(-5, 0, 0), 100, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(-5, 0, 0), 100, SmoothMovementUtils.quadraticRiseInOut()));

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("The thrust leaks laterally, causing unintended movement.");


        scene.idle(80);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, -10), 10, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(-0.25, 0, 0), 10, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(assembledStructure, new Vec3(5, 0, 0), 10, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(5, 0, 0), 10, SmoothMovementUtils.quadraticRiseInOut()));

        scene.idle(20);

        scene.addKeyframe();

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Using a Directional Gyroscopic Propeller Bearing allows you to offset and compensate for thrust leakage.");

        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(assembledStructure, new Vec3(0, 0, -5), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(-0.25, 0, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        scene.idle(100);

        scene.rotateCameraY(35);

        scene.overlay().showText(100)
                .placeNearTarget()
                .text("However, depending on the center of gravity, the contraption may tilt at a considerable angle, so caution is necessary.");

        scene.idle(50);

        scene.markAsFinished();

    }
}