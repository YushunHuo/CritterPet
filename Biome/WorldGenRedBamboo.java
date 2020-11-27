package Reika.CritterPet.Biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import Reika.CritterPet.CritterPet;
import Reika.CritterPet.Biome.BiomePinkForest.BiomeSection;
import Reika.DragonAPI.Libraries.Java.ReikaRandomHelper;

public class WorldGenRedBamboo extends WorldGenerator {

	private int generationRate = 128;

	WorldGenRedBamboo() {

	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		do {
			Block at = world.getBlock(x, y, z);
			if (!(at.isLeaves(world, x, y, z) || at.isAir(world, x, y, z))) {
				break;
			}
			y--;
		} while (y > 0);

		for (int i = 0; i < generationRate; i++) {
			int dx = x + rand.nextInt(8) - rand.nextInt(8);
			int dy = y + rand.nextInt(4) - rand.nextInt(4);
			int dz = z + rand.nextInt(8) - rand.nextInt(8);

			if (dy < 62 || !CritterPet.isPinkForest(world, dx, dz))
				continue;

			if (world.isAirBlock(dx, dy, dz) && !CritterPet.pinkforest.isRoad(world, dx, dz) && CritterPet.bamboo.canBlockStay(world, dx, dy, dz)) {
				int h = ReikaRandomHelper.getRandomBetween(3, 7, rand); //TODO noisemap?
				for (int d = 0; d < h; d++) {
					if (world.isAirBlock(dx, dy+d, dz)) {
						world.setBlock(dx, dy+d, dz, CritterPet.bamboo);
					}
					else {
						break;
					}
				}
			}
		}

		return true;
	}

	public void setFrequency(BiomeSection sub) {
		switch(sub) {
			case FOREST:
				generationRate = 32;
				break;
			case STREAMS:
				generationRate = 240;
				break;
			case SWAMP:
				generationRate = 72;
				break;
		}
	}
}