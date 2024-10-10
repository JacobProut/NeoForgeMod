package com.jacobpmods.datagen;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstNeoMod.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerModels() {
        basicItem(ModItems.fireball.get());
        basicItem(ModItems.speedapple.get());

        basicItem(ModItems.nexon.get());
        basicItem(ModItems.heatednexon.get());
        basicItem(ModItems.nexoningot.get());
        basicItem(ModItems.nexonreinforcedingot.get());


        handheldItem(ModItems.nexonpickaxe);
        handheldItem(ModItems.nexonhoe);
        handheldItem(ModItems.nexonshovel);
        handheldItem(ModItems.nexonsword);
        handheldItem(ModItems.nexonaxe);

        basicItem(ModItems.nexonhelmet.get());
        basicItem(ModItems.nexonchestplate.get());
        basicItem(ModItems.nexonleggings.get());
        basicItem(ModItems.nexonboots.get());


    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "item/" + item.getId().getPath()));
    }


}

