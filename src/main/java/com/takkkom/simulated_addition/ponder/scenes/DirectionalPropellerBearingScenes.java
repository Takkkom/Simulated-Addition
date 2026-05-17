package com.takkkom.simulated_addition.ponder.scenes;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import dev.eriksonn.aeronautics.content.ponder.instructions.ChangePropellerRotateInstruction;
import dev.eriksonn.aeronautics.content.ponder.instructions.PropellerRotateInstruction;
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
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class DirectionalPropellerBearingScenes {
    public static void thrustVectoring(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final SelectionUtil select = util.select();

        scene.title("thrust_vectoring", "Thrust Vectoring");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.8f);
        scene.setSceneOffsetY(0);

        final Selection structure = select.fromTo(1, 0, 0, 5, 2, 4);
        final BlockPos assembler = new BlockPos(3, 1, 2);
        final ElementLink<WorldSectionElement> assembledStructure = scene.world().showIndependentSectionImmediately(structure);

        final Selection propellerSailsStructure = select.fromTo(0, 0, 1, 0, 2, 3);
        final ElementLink<WorldSectionElement> propellerSails = scene.world().showIndependentSectionImmediately(propellerSailsStructure);

        BlockPos propellerBearingPos = new BlockPos(1, 1, 2);
        BlockPos propellerSailsPos = new BlockPos(0, 1, 2);
        scene.world().configureCenterOfRotation(propellerSails, Vec3.atCenterOf(propellerSailsPos));

        BlockPos enginePos = util.grid().at(2, 1, 2);
        scene.world().modifyBlockEntity(enginePos, PortableEngineBlockEntity.class, be -> {
            be.setCurrentBurnTime(SimItemService.INSTANCE.getBurnTime(Items.COAL.getDefaultInstance()));
        });

        scene.addInstruction(new PullTheAssemblerKronkInstruction(assembler, true, true));

        ArrayList<BlockPos> engineKinetics = new ArrayList<>();
        engineKinetics.add(enginePos);
        engineKinetics.add(util.grid().at(1, 1, 2));

        engineKinetics.forEach(engineKineticPos -> {
            Selection kinetic = util.select().position(engineKineticPos);
            scene.world().setKineticSpeed(kinetic, 32);
        });


        PropellerRotateInstruction propellerRotation = new PropellerRotateInstruction(propellerBearingPos, propellerSails, Direction.EAST,-32,2);
        scene.addInstruction(propellerRotation);
        scene.addInstruction(new ChangePropellerRotateInstruction.SetParticles(propellerRotation, propellerSailsPos,null,5f,5,1f,false));

        scene.idle(10);

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Directional Propeller Bearing tilts thrust in the direction of the redstone signal.")
                .pointAt(propellerBearingPos.getCenter());

        scene.idle(100);

        scene.addKeyframe();

        scene.overlay().showText(100)
                .placeNearTarget()
                .text("Therefore, it is useful for airships with simple structures and aircraft with extremely poor turning performance.")
                .pointAt(propellerBearingPos.getCenter());

        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(assembledStructure, new Vec3(0, 0, 10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, 10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0, -0.5, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        scene.idle(20);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(assembledStructure, new Vec3(0, 0, -10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, -10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0, 0.5, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        scene.idle(20);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(assembledStructure, new Vec3(0, 0, -10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, -10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0, 0.5, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        scene.idle(20);

        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(assembledStructure, new Vec3(0, 0, 10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.rotate(propellerSails, new Vec3(0, 0, 10), 20, SmoothMovementUtils.quadraticRiseInOut()));
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(propellerSails, new Vec3(0, -0.5, 0), 20, SmoothMovementUtils.quadraticRiseInOut()));

        scene.markAsFinished();
    }
}
