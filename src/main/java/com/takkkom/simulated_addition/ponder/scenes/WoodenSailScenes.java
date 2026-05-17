package com.takkkom.simulated_addition.ponder.scenes;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import dev.eriksonn.aeronautics.content.ponder.instructions.ChangePropellerRotateInstruction;
import dev.eriksonn.aeronautics.content.ponder.instructions.PropellerRotateInstruction;
import dev.simulated_team.simulated.ponder.SmoothMovementUtils;
import dev.simulated_team.simulated.ponder.instructions.CustomAnimateWorldSectionInstruction;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.api.scene.SelectionUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import oshi.util.tuples.Pair;

import java.util.ArrayList;

public class WoodenSailScenes {
    public static void lowAirResistanceAndLowLift(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final SelectionUtil select = util.select();

        scene.title("low_air_resistance_and_low_lift", "Low air resistance and low lift");
        scene.configureBasePlate(0, 0, 9);
        scene.scaleSceneView(0.8f);
        scene.setSceneOffsetY(0);
        scene.rotateCameraY(35);

        scene.showBasePlate();
        scene.idle(5);
        scene.world().showSection(select.layers(1, 2), Direction.DOWN);

        int speed = 64;

        ArrayList<Pair<Integer, BlockPos>> kineticBlocks = new ArrayList<>();
        kineticBlocks.add(new Pair<>(1, new BlockPos(4, 1, 8)));
        kineticBlocks.add(new Pair<>(-1, new BlockPos(4, 1, 7)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(4, 1, 6)));
        kineticBlocks.add(new Pair<>(-1, new BlockPos(4, 1, 5)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(4, 1, 4)));

        kineticBlocks.add(new Pair<>(-1, new BlockPos(5, 1, 4)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(6, 1, 4)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(6, 2, 4)));

        kineticBlocks.add(new Pair<>(-1, new BlockPos(3, 1, 4)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(2, 1, 4)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(2, 2, 4)));

        kineticBlocks.add(new Pair<>(1, new BlockPos(4, 5, 8)));
        kineticBlocks.add(new Pair<>(-1, new BlockPos(4, 5, 7)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(4, 5, 6)));
        kineticBlocks.add(new Pair<>(-1, new BlockPos(4, 5, 5)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(4, 5, 4)));

        kineticBlocks.add(new Pair<>(-1, new BlockPos(5, 5, 4)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(6, 5, 4)));

        kineticBlocks.add(new Pair<>(-1, new BlockPos(3, 5, 4)));
        kineticBlocks.add(new Pair<>(1, new BlockPos(2, 5, 4)));

        scene.idle(5);

        BlockPos propellerBearingPosRight = new BlockPos(2, 2, 4);
        BlockPos propellerSailsPosRight = new BlockPos(2, 3, 4);

        BlockPos propellerBearingPosLeft = new BlockPos(6, 2, 4);
        BlockPos propellerSailsPosLeft = new BlockPos(6, 3, 4);


        final Selection propellerSailsRightStructure = select.fromTo(1, 3, 3, 3, 3, 5);
        final ElementLink<WorldSectionElement> propellerSailsRight = scene.world().showIndependentSection(propellerSailsRightStructure, Direction.DOWN);
        scene.world().configureCenterOfRotation(propellerSailsRight, Vec3.atCenterOf(propellerSailsPosRight));

        PropellerRotateInstruction propellerRotationRight = new PropellerRotateInstruction(propellerBearingPosRight, propellerSailsRight, Direction.DOWN, speed,4);
        scene.addInstruction(propellerRotationRight);
        scene.addInstruction(new ChangePropellerRotateInstruction.SetParticles(propellerRotationRight, propellerSailsPosRight,null,5f,-5,1.5f,false));


        final Selection propellerSailsLeftStructure = select.fromTo(5, 3, 3, 7, 3, 5);
        final ElementLink<WorldSectionElement> propellerSailsLeft = scene.world().showIndependentSection(propellerSailsLeftStructure, Direction.DOWN);
        scene.world().configureCenterOfRotation(propellerSailsLeft, Vec3.atCenterOf(propellerSailsPosLeft));

        PropellerRotateInstruction propellerRotationLeft = new PropellerRotateInstruction(propellerBearingPosLeft, propellerSailsLeft, Direction.DOWN, speed,4);
        scene.addInstruction(propellerRotationLeft);
        scene.addInstruction(new ChangePropellerRotateInstruction.SetParticles(propellerRotationLeft, propellerSailsPosLeft,null,5f,-5,1.5f,false));


        kineticBlocks.forEach(x -> {
            Selection kinetic = util.select().position(x.getB());
            scene.world().setKineticSpeed(kinetic, speed * x.getA());
        });

        scene.idle(20);

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Wooden sails and wooden symmetric sails have half the thrust, lift, and air resistance compared to wool sails.");

        scene.idle(100);

        scene.world().hideSection(select.layers(1, 2), Direction.UP);
        scene.world().hideIndependentSection(propellerSailsRight, Direction.UP);
        scene.world().hideIndependentSection(propellerSailsLeft, Direction.UP);
        scene.addInstruction(new ChangePropellerRotateInstruction.StopParticles(propellerRotationRight));
        scene.addInstruction(new ChangePropellerRotateInstruction.StopParticles(propellerRotationLeft));


        scene.idle(20);

        final Selection sailStructure = select.fromTo(0, 5, 0, 8, 6, 8);
        final ElementLink<WorldSectionElement> sailAssembledStructure = scene.world().showIndependentSection(sailStructure, Direction.DOWN);
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(sailAssembledStructure, new Vec3(0, -4, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        final Selection windmillSailsRightStructure = select.fromTo(1, 7, 3, 3, 10, 5);
        final ElementLink<WorldSectionElement> windmillSailsRight = scene.world().showIndependentSection(windmillSailsRightStructure, Direction.DOWN);
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(windmillSailsRight, new Vec3(0, -4, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        final Selection windmillSailsLeftStructure = select.fromTo(5, 7, 3, 7, 8, 5);
        final ElementLink<WorldSectionElement> windmillSailsLeft = scene.world().showIndependentSection(windmillSailsLeftStructure, Direction.DOWN);
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(windmillSailsLeft, new Vec3(0, -4, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        int windmillSpeed = 1;

        scene.world().rotateBearing(new BlockPos(2, 6, 4), 360, 1200 / windmillSpeed);
        scene.world().rotateSection(windmillSailsRight, 0, 360, 0, 1200 / windmillSpeed);
        scene.world().rotateBearing(new BlockPos(6, 6, 4), 360, 1200 / windmillSpeed);
        scene.world().rotateSection(windmillSailsLeft, 0, 360, 0, 1200 / windmillSpeed);

        kineticBlocks.forEach(x -> {
            Selection kinetic = util.select().position(x.getB());
            scene.world().setKineticSpeed(kinetic, windmillSpeed);
        });

        scene.idle(40);

        scene.addKeyframe();

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Furthermore, when used as sails for a windmill, at least eight are required.");

        scene.idle(80);

        scene.markAsFinished();
    }
}
