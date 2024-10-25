
package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider  extends BlockLootSubProvider {
    protected  ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }


    @Override
    protected void generate() {

        dropSelf(ModBlocks.NEXON_BLOCK.get());
        dropSelf(ModBlocks.NEXON_ORE_BLOCK.get());
        dropSelf(ModBlocks.GHOSTLY_GRASS_BLOCK.get());
        dropSelf(ModBlocks.GHOSTLY_DIRT.get());
        dropSelf(ModBlocks.GHOSTLY_WEB.get());
        dropSelf(ModBlocks.GHOSTLY_STONE.get());
        dropSelf(ModBlocks.PEDESTAL.get());

        this.add(ModBlocks.NEXON_ORE_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.NEXON_ORE_BLOCK.get(), ModItems.nexon.get(), 1, 2));

        this.dropSelf(ModBlocks.LOG_GHOSTLY.get());
        this.dropSelf(ModBlocks.STRIPPED_GHOSTLY_LOG.get());
        this.dropSelf(ModBlocks.WOOD_GHOSTLY.get());
        this.dropSelf(ModBlocks.STRIPPED_GHOSTLY_WOOD.get());
        this.dropSelf(ModBlocks.PLANKS_GHOSTLY.get());
        this.dropSelf(ModBlocks.GHOSTLY_SAPLING.get());

        this.add(ModBlocks.GHOSTLY_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.GHOSTLY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

    }


    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected  Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}

