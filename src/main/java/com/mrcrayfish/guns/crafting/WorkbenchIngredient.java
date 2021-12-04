package com.mrcrayfish.guns.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Author: MrCrayfish
 */
public class WorkbenchIngredient extends Ingredient
{
    private final IItemList itemList;
    private final int count;

    protected WorkbenchIngredient(Stream<? extends IItemList> itemList, int count)
    {
        super(itemList);
        this.itemList = null;
        this.count = count;
    }

    private WorkbenchIngredient(IItemList itemList, int count)
    {
        super(Stream.of(itemList));
        this.itemList = itemList;
        this.count = count;
    }

    public int getCount()
    {
        return this.count;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer()
    {
        return Serializer.INSTANCE;
    }

    public static WorkbenchIngredient fromJson(JsonObject object)
    {
        Ingredient.IItemList value = deserializeItemList(object);
        int count = JSONUtils.getInt(object, "count", 1);
        return new WorkbenchIngredient(Stream.of(value), count);
    }

    @Override
    public JsonElement serialize()
    {
        JsonObject object = this.itemList.serialize();
        object.addProperty("count", this.count);
        return object;
    }

    public static WorkbenchIngredient of(IItemProvider provider, int count)
    {
        return new WorkbenchIngredient(new Ingredient.SingleItemList(new ItemStack(provider)), count);
    }

    public static WorkbenchIngredient of(ItemStack stack, int count)
    {
        return new WorkbenchIngredient(new Ingredient.SingleItemList(stack), count);
    }

    public static WorkbenchIngredient of(ITag<Item> tag, int count)
    {
        return new WorkbenchIngredient(new Ingredient.TagList(tag), count);
    }

    public static WorkbenchIngredient of(ResourceLocation id, int count)
    {
        return new WorkbenchIngredient(new MissingSingleItemList(id), count);
    }

    public static class Serializer implements IIngredientSerializer<WorkbenchIngredient>
    {
        public static final WorkbenchIngredient.Serializer INSTANCE = new WorkbenchIngredient.Serializer();

        @Override
        public WorkbenchIngredient parse(PacketBuffer buffer)
        {
            int itemCount = buffer.readVarInt();
            int count = buffer.readVarInt();
            Stream<Ingredient.SingleItemList> values = Stream.generate(() -> new SingleItemList(buffer.readItemStack())).limit(itemCount);
            return new WorkbenchIngredient(values, count);
        }

        @Override
        public WorkbenchIngredient parse(JsonObject object)
        {
            return WorkbenchIngredient.fromJson(object);
        }

        @Override
        public void write(PacketBuffer buffer, WorkbenchIngredient ingredient)
        {
            buffer.writeVarInt(ingredient.getMatchingStacks().length);
            buffer.writeVarInt(ingredient.count);
            for(ItemStack stack : ingredient.getMatchingStacks())
            {
                buffer.writeItemStack(stack);
            }
        }
    }

    /**
     * Allows ability to define an ingredient from another mod without adding it as a dependency in
     * the development environment. Serializes the data to be read by the regular
     * {@link SingleItemList}. Only use this for generating data.
     */
    public static class MissingSingleItemList implements Ingredient.IItemList
    {
        private final ResourceLocation id;

        public MissingSingleItemList(ResourceLocation id)
        {
            this.id = id;
        }

        @Override
        public Collection<ItemStack> getStacks()
        {
            return Collections.emptyList();
        }

        @Override
        public JsonObject serialize()
        {
            JsonObject object = new JsonObject();
            object.addProperty("item", this.id.toString());
            return object;
        }
    }
}
