package org.cloudburstmc.api.level;

import com.nukkitx.math.vector.Vector2i;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.math.vector.Vector3i;
import com.nukkitx.math.vector.Vector4i;
import org.cloudburstmc.api.block.Block;
import org.cloudburstmc.api.block.BlockState;
import org.cloudburstmc.api.level.chunk.Chunk;
import org.cloudburstmc.api.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public interface ChunkManager {

    default BlockState getBlockState(Vector3i pos) {
        return getBlockState(pos.getX(), pos.getY(), pos.getZ(), 0);
    }

    default BlockState getBlockState(Vector3i pos, int layer) {
        return getBlockState(pos.getX(), pos.getY(), pos.getZ(), layer);
    }

    default BlockState getBlockState(int x, int y, int z) {
        return this.getBlockState(x, y, z, 0);
    }

    default BlockState getBlockState(Vector4i pos) {
        return getBlockState(pos.getX(), pos.getY(), pos.getZ(), pos.getW());
    }

    BlockState getBlockState(int x, int y, int z, int layer);

    default Block getBlock(Vector3i pos) {
        return getBlock(pos.getX(), pos.getY(), pos.getZ());
    }

    default Block getBlock(Vector3f pos) {
        return getBlock(pos.toInt());
    }

    Block getBlock(int x, int y, int z);

    default Block getLoadedBlock(Vector3i pos) {
        return getLoadedBlock(pos.getX(), pos.getY(), pos.getZ());
    }

    default Block getLoadedBlock(Vector3f pos) {
        return getLoadedBlock(pos.toInt());
    }

    Block getLoadedBlock(int x, int y, int z);

    default boolean setBlockState(Vector3i pos, BlockState blockState) {
        return this.setBlockState(pos.getX(), pos.getY(), pos.getZ(), 0, blockState);
    }

    default boolean setBlockState(Vector3i pos, int layer, BlockState blockState) {
        return this.setBlockState(pos.getX(), pos.getY(), pos.getZ(), layer, blockState);
    }

    default boolean setBlockState(Vector3i pos, BlockState blockState, boolean direct) {
        return this.setBlockState(pos, blockState, direct, true);
    }

    default boolean setBlockState(Vector3i pos, int layer, BlockState blockState, boolean direct, boolean update) {
        return setBlockState(pos.getX(), pos.getY(), pos.getZ(), layer, blockState, direct, update);
    }

    default boolean setBlockState(Vector3i pos, BlockState blockState, boolean direct, boolean update) {
        return setBlockState(pos.getX(), pos.getY(), pos.getZ(), 0, blockState, direct, update);
    }

    default boolean setBlockState(Vector4i pos, BlockState blockState) {
        return this.setBlockState(pos, blockState, false);
    }

    default boolean setBlockState(Vector4i pos, BlockState blockState, boolean direct) {
        return this.setBlockState(pos, blockState, direct, true);
    }

    default boolean setBlockState(Vector4i pos, BlockState blockState, boolean direct, boolean update) {
        return setBlockState(pos.getX(), pos.getY(), pos.getZ(), pos.getW(), blockState, direct, update);
    }

    default boolean setBlockState(int x, int y, int z, BlockState state) {
        return setBlockState(x, y, z, 0, state, false, true);
    }

    default boolean setBlockState(int x, int y, int z, int layer, BlockState state) {
        return setBlockState(x, y, z, layer, state, false, true);
    }

    boolean setBlockState(int x, int y, int z, int layer, BlockState state, boolean direct, boolean update);

    @Nonnull
    default Chunk getChunk(Vector3f pos) {
        return getChunk(pos.toInt());
    }

    @Nonnull
    default Chunk getChunk(Vector3i pos) {
        return getChunk(pos.getX() >> 4, pos.getZ() >> 4);
    }

    @Nonnull
    default Chunk getChunk(Vector2i chunkPos) {
        return getChunk(chunkPos.getX(), chunkPos.getY());
    }

    @Nonnull
    default Chunk getChunk(int chunkX, int chunkZ) {
        return getChunk((((long) chunkX) << 32) | (chunkZ & 0xffffffffL));
    }

    @Nonnull
    Chunk getChunk(long key);

    @Nullable
    default Chunk getLoadedChunk(Vector3f position) {
        return getLoadedChunk(position.getFloorX() >> 4, position.getFloorZ() >> 4);
    }

    @Nullable
    default Chunk getLoadedChunk(Vector3i position) {
        return getLoadedChunk(position.getX(), position.getZ());
    }

    @Nullable
    default Chunk getLoadedChunk(int chunkX, int chunkZ) {
        return getLoadedChunk((((long) chunkX) << 32) | (chunkZ & 0xffffffffL));
    }

    @Nullable
    Chunk getLoadedChunk(long key);

    @Nonnull
    CompletableFuture<? extends Chunk> getChunkFuture(int chunkX, int chunkZ);

    long getSeed();

    @Nonnull
    Set<? extends Chunk> getChunks();

    Set<? extends Player> getChunkPlayers(int chunkX, int chunkZ);

    Set<? extends ChunkLoader> getChunkLoaders(int chunkX, int chunkZ);
}