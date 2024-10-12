package com.jacobpmods.datagen;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.ItemTags.*;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, FirstNeoMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS);
                /*.add(ModItems.item.get())*/

        tag(LOGS_THAT_BURN)
                .add(ModBlocks.LOG_GHOSTLY.get().asItem())
                .add(ModBlocks.WOOD_GHOSTLY.get().asItem())
                .add(ModBlocks.STRIPPED_GHOSTLY_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_GHOSTLY_WOOD.get().asItem());

        tag(PLANKS)
                .add(ModBlocks.PLANKS_GHOSTLY.get().asItem());


        tag(SWORDS)
                .add(ModItems.nexonsword.get());

        tag(SWORD_ENCHANTABLE)
                .add(ModItems.nexonsword.get());

        tag(MINING_ENCHANTABLE)
                .add(ModItems.nexonpickaxe.get());

        tag(ARMOR_ENCHANTABLE)
                .add(ModItems.nexonhelmet.get())
                .add(ModItems.nexonchestplate.get())
                .add(ModItems.nexonleggings.get())
                .add(ModItems.nexonboots.get());

        //Unbreaking 3 Enchantment
        tag(DURABILITY_ENCHANTABLE)
                .add(ModItems.nexonhelmet.get())
                .add(ModItems.nexonchestplate.get())
                .add(ModItems.nexonleggings.get())
                .add(ModItems.nexonboots.get())
                .add(ModItems.nexonsword.get())
                .add(ModItems.nexonaxe.get())
                .add(ModItems.nexonhoe.get())
                .add(ModItems.nexonshovel.get());


        tag(HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.nexonhelmet.get());

        tag(CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.nexonchestplate.get());

        tag(LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.nexonleggings.get());

        tag(FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.nexonboots.get());

        this.tag(TRIMMABLE_ARMOR)
                .add(ModItems.nexonhelmet.get())
                .add(ModItems.nexonchestplate.get())
                .add(ModItems.nexonleggings.get())
                .add(ModItems.nexonboots.get());

    }
}
