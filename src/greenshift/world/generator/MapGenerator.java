package greenshift.world.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import greenshift.Reference;
import greenshift.util.SystemGrabber;

public class MapGenerator {
	private static final String BIOME_FILE = SystemGrabber.getAppdataPath() + File.separator + Reference.TITLE + "Biomes.txt";
	//TODO: Dynamically find this file.

	private final long seed;
	private Random seedGenerator;
	private final BiomeGenerator biomeGenerator;

	public MapGenerator(long seed) {
		this.seed = seed;
		seedGenerator = new Random(seed);
		biomeGenerator = new BiomeGenerator(new File(BIOME_FILE));
	}

	public long getNewSeed() {
		return seedGenerator.nextLong();
	}

	private class PathGenerator {

	}

	/**
	 * Generates a biome map based on noise values for multiple aspects.
	 * @author geekman9097
	 *
	 */
	private class BiomeGenerator {
		//		private final File biomeFile; //which file to read to load data
		private final DataReader dataReader; //reads the data off the file.
		private final int depth; //how many bases of generation to use.

		private final OpenSimplexNoise[] noiseGenerators; //the group of noise planes to draw our data from
		private final Map<String,List<String>> biomeGroups; //a list of the calls for biomes that can be generated, for some data values
		private final Random biomeGrabber; //a random generator for selecting which biome from the possible group to use.
		

		BiomeGenerator(File readFile) {
			
			//set up data storage
			biomeGroups = new HashMap<>();
			//initialize the random with a new seed from the map generator
			biomeGrabber = new Random(getNewSeed());
			//create a data reader
			dataReader = new DataReader(readFile);
			
			//find out how many generators we need to use
			depth = dataReader.loadNextLine().getLineAsData().length;

			//Load data into collections
			noiseGenerators = new OpenSimplexNoise[depth];
			for(int i = 0; i<depth; i++) { noiseGenerators[i] = new OpenSimplexNoise(getNewSeed()); }
			loadBiomes();
		}

		private void loadBiomes() {
			String[] data = {""};
			while(data[0] != DataReader.END_OF_FILE) {
				String name = data[0]; 
				int[] values = new int[depth];
				for(int i = 0; i < depth; i++) {
					values[i] = Integer.parseInt(data[i+1]);
				}
				addBiome(values,name);
			
			data = dataReader.loadNextLine().getLineAsValues();
			}
		}

		private void addBiome(int[] values, String biomeName) {
			String key = valuesToKey(values);
			biomeGroups.putIfAbsent(key,new ArrayList<>());
			biomeGroups.get(key).add(biomeName);
		}

		private String valuesToKey(int[] values) {
			StringBuilder result = new StringBuilder("");
			for(int i : values) {
				result.append(i);
			}
			return result.toString();
		}

		private List<String> getBiomeNames(int[] values) {
			String key = valuesToKey(values);
			return biomeGroups.get(key);
		}

		private String getRandomBiomeName(List<String> biomes){
			int index = biomeGrabber.nextInt(biomes.size());
			return biomes.get(index);
		}

	}
}
