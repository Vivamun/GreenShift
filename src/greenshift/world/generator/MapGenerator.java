package greenshift.world.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import greenshift.Reference;
import greenshift.util.SystemGrabber;
import greenshift.world.Direction;
import greenshift.world.Doorway;
import greenshift.world.Pos;

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
	
	public BiomeGenerator getBiomeGen() {
		return biomeGenerator;
	}

	private long getNewSeed() {
		return seedGenerator.nextLong();
	}
	
	/**
	 * A path generation class for creating connections between rooms
	 * When handed a room, and a direction, must be able to create either a doorway between that room and the room in that direction,
	 * or no door at all, representing a closed wall
	 * 
	 * @author DMWCincy
	 */
	private class PathGenerator {
		
		private Direction in;
		
		//TODO: Implement Pos class
		private Doorway getDoor(Pos position, Direction out) {
			return null;
		}
		
		
		
	}

	/**
	 * Generates a biome map based on noise values for multiple aspects.
	 * @author geekman9097
	 *
	 */
	public class BiomeGenerator {
		private final DataReader dataReader; //reads the data off the file.
		private final int complexity; //how many bases of generation to use.
		private final int[] precision; //the highest value found for generation for each generator 

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
			complexity = dataReader.loadNextLine().getLineAsData().length;

			//Load data into collections
			noiseGenerators = new OpenSimplexNoise[complexity];
			for(int i = 0; i<complexity; i++) { noiseGenerators[i] = new OpenSimplexNoise(getNewSeed()); }
			precision = loadBiomes();
		}

		private int[] loadBiomes() {
			int[] tempPrecision = new int[complexity];
			String[] data = {""};
			while(data[0] != DataReader.END_OF_FILE) {
				String name = data[0]; 
				int[] values = new int[complexity];
				for(int i = 0; i < complexity; i++) {
					values[i] = Integer.parseInt(data[i+1]);
					if(values[i] > tempPrecision[i]) tempPrecision[i] = values[i];
				}
				addBiome(values,name);
			
			data = dataReader.loadNextLine().getLineAsValues();
			}
			return tempPrecision;
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
		
		private int[] getValuesAt(int x, int z) {
			int[] result = new int[complexity];
			for (int i = 0; i < complexity; i++) {
				result[i] = noiseToKey(noiseGenerators[i].eval(x, z),i);
			}
			return result;
		}

		/**
		 * converts a 2D noise value (within <code>{-sqrt(1/2),sqrt(1/2)}</code>)
		 * to a key value (within <code>{0, precision[generatorNumber]}</code>)
		 * @param noise
		 * @param generatorNumber
		 * @return
		 */
		private int noiseToKey(double noise, int generatorNumber) {
			int maxVal = precision[generatorNumber];
			//normalize the value from 0 to 1
			double dataVal = (noise - Math.sqrt(1/2))/(Math.sqrt(2));
			//convert the data val to a non-rounded key
			double keyVal = dataVal*maxVal;
			//round to nearest integer
			int key = (int)(keyVal + 0.5);
			return key;
		}

		private List<String> getBiomeNames(int[] values) {
			String key = valuesToKey(values);
			return biomeGroups.get(key);
		}

		private String getRandomBiomeName(List<String> biomes){
			int index = biomeGrabber.nextInt(biomes.size());
			return biomes.get(index);
		}
		
		public String getBiomeAt(int x, int z) {
			return getRandomBiomeName(
				getBiomeNames(
					getValuesAt(x,z)
				)
			);
		}

	}
}
	
