package com.takkkom.simulated_addition.ponder.scenes;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import dev.simulated_team.simulated.ponder.SmoothMovementUtils;
import dev.simulated_team.simulated.ponder.instructions.CustomAnimateWorldSectionInstruction;
import dev.simulated_team.simulated.ponder.instructions.PullTheAssemblerKronkInstruction;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.api.scene.SelectionUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class BallasiteScenes {
    public static void veryHeavy(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        final SelectionUtil select = util.select();

        scene.title("very_heavy", "Very Heavy");
        scene.configureBasePlate(0, 0, 19);
        scene.scaleSceneView(0.4f);
        scene.setSceneOffsetY(-15);
        scene.rotateCameraY(35);

        scene.world().showSection(select.layers(0, 12), Direction.UP);

        scene.idle(5);

        final Selection floatiteStructure = select.fromTo(3, 14, 7, 7, 33, 11);
        final BlockPos floatiteAssembler = new BlockPos(5, 33, 9);
        final ElementLink<WorldSectionElement> floatiteAssembledStructure = scene.world().showIndependentSection(floatiteStructure, Direction.DOWN);

        scene.idle(5);

        final Selection levititeStructure = select.fromTo(11, 14, 7, 15, 32, 11);
        final BlockPos levititeAssembler = new BlockPos(13, 32, 9);
        final ElementLink<WorldSectionElement> levititeAssembledStructure = scene.world().showIndependentSection(levititeStructure, Direction.DOWN);

        scene.idle(20);

        scene.overlay().showText(80)
                .placeNearTarget()
                .text("Ballasite has a very high mass and is excellent at adjusting the center of gravity.")
                .pointAt(new Vec3(5, 15, 9));

        scene.idle(80);

        scene.overlay().showControls(util.vector().centerOf(floatiteAssembler), Pointing.DOWN, 20).rightClick();
        scene.overlay().showControls(util.vector().centerOf(levititeAssembler), Pointing.DOWN, 20).rightClick();
        scene.addInstruction(new PullTheAssemblerKronkInstruction(floatiteAssembler, true, true));
        scene.addInstruction(new PullTheAssemblerKronkInstruction(levititeAssembler, true, true));

        scene.idle(20);

        scene.addKeyframe();

        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(floatiteAssembledStructure, new Vec3(0, -15, 0), 60, SmoothMovementUtils.quadraticRiseInOut()));
        scene.idle(60);
        scene.addInstruction(CustomAnimateWorldSectionInstruction.move(floatiteAssembledStructure, new Vec3(0, 2, 0), 30, SmoothMovementUtils.quadraticRiseInOut()));

        scene.idle(20);

        scene.markAsFinished();
    }
}
