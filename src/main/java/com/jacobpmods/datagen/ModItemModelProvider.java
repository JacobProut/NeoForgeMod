package com.jacobpmods.datagen;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstNeoMod.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerModels() {
        //basicItem(ModItems.fireball.get());
        //basicItem(ModItems.speedapple.get());

        basicItem(ModItems.nexon.get());
        basicItem(ModItems.heatednexon.get());
        //basicItem(ModItems.nexoningot.get());
        //basicItem(ModItems.nexonpickaxe.get());
       //basicItem(ModItems.nexonreinforcedingot.get());

        //handheldItem(ModItems.nexonpickaxe);
        //handheldItem(ModItems.nexonhoe);
        //handheldItem(ModItems.nexonshovel);
        //handheldItem(ModItems.nexonsword);
        //handheldItem(ModItems.nexonaxe)

    }

}

