package com.jacobpmods.datagen;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, FirstNeoMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS);
                /*.add(ModItems.nexon.get())
                .add(ModItems.heatednexon.get())
                .add(ModItems.nexoningot.get())
                .add(ModItems.nexonreinforcedingot.get());*/
    }
}
