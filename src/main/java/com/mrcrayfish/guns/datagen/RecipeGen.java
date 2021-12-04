package com.mrcrayfish.guns.datagen;

import com.google.gson.JsonObject;
import com.mrcrayfish.guns.Reference;
import com.mrcrayfish.guns.crafting.WorkbenchIngredient;
import com.mrcrayfish.guns.crafting.WorkbenchRecipeBuilder;
import com.mrcrayfish.guns.init.ModBlocks;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.init.ModRecipeSerializers;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider
{
    public RecipeGen(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        // Dye Item
        consumer.accept(new IFinishedRecipe()
        {
            @Override
            public void serialize(JsonObject json)
            {
            }

            @Override
            public IRecipeSerializer<?> getSerializer()
            {
                return ModRecipeSerializers.DYE_ITEM.get();
            }

            @Override
            public ResourceLocation getID()
            {
                return new ResourceLocation(Reference.MOD_ID, "dye_item");
            }

            @Override
            @Nullable
            public JsonObject getAdvancementJson()
            {
                return null;
            }

            @Override
            public ResourceLocation getAdvancementID()
            {
                return new ResourceLocation("");
            }
        });

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.WORKBENCH.get())
                .patternLine("CCC")
                .patternLine("III")
                .patternLine("I I")
                .key('C', Blocks.LIGHT_GRAY_CONCRETE)
                .key('I', Tags.Items.INGOTS_IRON)
                .addCriterion("has_concrete", hasItem(Blocks.LIGHT_GRAY_CONCRETE))
                .addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);

        // Guns
        WorkbenchRecipeBuilder.crafting(ModItems.PISTOL.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 14))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.SHOTGUN.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 24))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.RIFLE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 24))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.GRENADE_LAUNCHER.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 32))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.BAZOOKA.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 44))
                .addIngredient(Items.REDSTONE, 4)
                .addIngredient(WorkbenchIngredient.of(Tags.Items.DYES_RED, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .addCriterion("has_redstone", hasItem(Items.REDSTONE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.MINI_GUN.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 38))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.ASSAULT_RIFLE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 28))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.MACHINE_PISTOL.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 20))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.HEAVY_RIFLE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.INGOTS_IRON, 36))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);

        // Ammo
        WorkbenchRecipeBuilder.crafting(ModItems.BASIC_BULLET.get(), 32) // Why is the basic bullet the same price as the advanced?
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 4))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 1))
                .addCriterion("has_iron_nugget", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gunpowder", hasItem(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.ADVANCED_AMMO.get(), 16)
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 4))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 1))
                .addCriterion("has_iron_nugget", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gunpowder", hasItem(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.SHELL.get(), 24)
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 4))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_GOLD, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 1))
                .addCriterion("has_iron_nugget", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gold_nugget", hasItem(Tags.Items.NUGGETS_GOLD))
                .addCriterion("has_gunpowder", hasItem(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.MISSILE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 2))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 4))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gunpowder", hasItem(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.GRENADE.get(), 2)
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 4))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gunpowder", hasItem(Tags.Items.GUNPOWDER))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.STUN_GRENADE.get(), 2)
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GUNPOWDER, 2))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.DUSTS_GLOWSTONE, 4))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gunpowder", hasItem(Tags.Items.GUNPOWDER))
                .addCriterion("has_glowstone", hasItem(Tags.Items.DUSTS_GLOWSTONE))
                .build(consumer);

        // Scope Attachments
        WorkbenchRecipeBuilder.crafting(ModItems.SHORT_SCOPE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 2))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GLASS_PANES, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.DUSTS_REDSTONE, 2))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_glass_pane", hasItem(Tags.Items.GLASS_PANES))
                .addCriterion("has_redstone", hasItem(Tags.Items.DUSTS_REDSTONE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.MEDIUM_SCOPE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 4))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GLASS_PANES, 1))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.DUSTS_REDSTONE, 4))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_glass_pane", hasItem(Tags.Items.GLASS_PANES))
                .addCriterion("has_redstone", hasItem(Tags.Items.DUSTS_REDSTONE))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.LONG_SCOPE.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 6))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.GLASS_PANES, 2))
                .addIngredient(WorkbenchIngredient.of(Tags.Items.DYES_BLACK, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_glass_pane", hasItem(Tags.Items.GLASS_PANES))
                .addCriterion("has_black_dye", hasItem(Tags.Items.DYES_BLACK))
                .build(consumer);

        // Barrel Attachments
        WorkbenchRecipeBuilder.crafting(ModItems.SILENCER.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 4))
                .addIngredient(WorkbenchIngredient.of(Items.SPONGE, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .build(consumer);

        // Stock Attachments
        WorkbenchRecipeBuilder.crafting(ModItems.LIGHT_STOCK.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 6))
                .addIngredient(WorkbenchIngredient.of(Items.GRAY_WOOL, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gray_wool", hasItem(Items.GRAY_WOOL))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.TACTICAL_STOCK.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 8))
                .addIngredient(WorkbenchIngredient.of(Items.GRAY_WOOL, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gray_wool", hasItem(Items.GRAY_WOOL))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.WEIGHTED_STOCK.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 12))
                .addIngredient(WorkbenchIngredient.of(Items.GRAY_WOOL, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gray_wool", hasItem(Items.GRAY_WOOL))
                .build(consumer);

        // Under Barrel Attachments
        WorkbenchRecipeBuilder.crafting(ModItems.LIGHT_GRIP.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 4))
                .addIngredient(WorkbenchIngredient.of(Items.GRAY_WOOL, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gray_wool", hasItem(Items.GRAY_WOOL))
                .build(consumer);
        WorkbenchRecipeBuilder.crafting(ModItems.SPECIALISED_GRIP.get())
                .addIngredient(WorkbenchIngredient.of(Tags.Items.NUGGETS_IRON, 8))
                .addIngredient(WorkbenchIngredient.of(Items.GRAY_WOOL, 1))
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.NUGGETS_IRON))
                .addCriterion("has_gray_wool", hasItem(Items.GRAY_WOOL))
                .build(consumer);
    }
}