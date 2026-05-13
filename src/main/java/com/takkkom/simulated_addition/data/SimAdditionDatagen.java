package com.takkkom.simulated_addition.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simibubi.create.foundation.utility.FilesHelper;
import com.takkkom.simulated_addition.SimulatedAddition;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class SimAdditionDatagen {
    public static void gatherDataHighPriority(GatherDataEvent event) {
        if (event.getMods().contains(SimulatedAddition.MODID))
            addExtraRegistrateData();
    }

    public static void gatherData(GatherDataEvent event) {
        if (!event.getMods().contains(SimulatedAddition.MODID))
            return;

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //generator.addProvider(event.includeClient(), AllSoundEvents.provider(generator));

        /*
        generator.addProvider(event.includeServer(), new CreateRecipeSerializerTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new CreateContraptionTypeTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new CreateMountedItemStorageTypeTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new DamageTypeTagGen(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new AllAdvancements(output, lookupProvider));
         */
         /*
        generator.addProvider(event.includeServer(), new CreateMechanicalCraftingRecipeGen(output, lookupProvider));
        generator.addProvider(event.includeServer(), new CreateSequencedAssemblyRecipeGen(output, lookupProvider));
        generator.addProvider(event.includeServer(), new CreateDatamapProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new VanillaHatOffsetGenerator(output, lookupProvider));
        generator.addProvider(event.includeServer(), new CuriosDataGenerator(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new CreateEnchantmentTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeClient(), new CreateWikiBlockInfoProvider(output));
          */

        if (event.includeServer()) {
            //CreateRecipeProvider.registerAllProcessing(generator, output, lookupProvider);
        }
    }

    private static void addExtraRegistrateData() {
        SimAdditionRegistrateTags.addGenerators();

        SimulatedAddition.registrate().addDataGenerator(ProviderType.LANG, provider -> {
            BiConsumer<String, String> langConsumer = provider::add;

            provideDefaultLang("interface", langConsumer);
            provideDefaultLang("tooltips", langConsumer);
            //AllAdvancements.provideLang(langConsumer);
            //AllSoundEvents.provideLang(langConsumer);
            //AllKeys.provideLang(langConsumer);
            providePonderLang(langConsumer);
            //new TagLangGenerator(langConsumer).generate();
        });
    }

    private static void provideDefaultLang(String fileName, BiConsumer<String, String> consumer) {
        String path = "assets/" + SimulatedAddition.MODID + "/lang/default/" + fileName + ".json";
        JsonElement jsonElement = FilesHelper.loadJsonResource(path);
        if (jsonElement == null) {
            throw new IllegalStateException(String.format("Could not find default lang file: %s", path));
        }
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().getAsString();
            consumer.accept(key, value);
        }
    }

    private static void providePonderLang(BiConsumer<String, String> consumer) {
        // Register this since FMLClientSetupEvent does not run during datagen
        /*
        PonderIndex.addPlugin(new CreatePonderPlugin());

        PonderIndex.getLangAccess().provideLang(Create.ID, consumer);
         */
    }
}
