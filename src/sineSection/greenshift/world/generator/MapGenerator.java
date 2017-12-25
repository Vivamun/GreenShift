package sineSection.greenshift.world.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import sineSection.greenshift.ApplicationData;
import sineSection.greenshift.GreenShift;
import sineSection.greenshift.launcher.FileReader;
import sineSection.greenshift.launcher.parser.RoomParser;
import sineSection.greenshift.world.Direction;
import sineSection.greenshift.world.Doorway;
import sineSection.greenshift.world.Pos;
import sineSection.greenshift.world.Room;

public class MapGenerator {

	private final long seed;
	private Random seedGenerator;
	private final BiomeGenerator biomeGenerator;
	private final PathGenerator pathGenerator;

	public MapGenerator(long seed) {
		this.seed = seed;
		seedGenerator = new Random(seed);
		biomeGenerator = new BiomeGenerator(ApplicationData.readFile("rooms" + File.separator + "biomes.txt"));
		pathGenerator = new PathGenerator();
	}

	public BiomeGenerator getBiomeGen() {
		return biomeGenerator;
	}

	public PathGenerator getPathGen() {
		return pathGenerator;
	}

	private long getNewSeed() {
		return seedGenerator.nextLong();
	}

	/**
	 * A path generation class for creating connections between rooms When
	 * handed a room, and a direction, must be able to create either a doorway
	 * between that room and the room in that direction, or no door at all,
	 * representing a closed wall
	 * 
	 * @author DMWCincy, geekman9097
	 */
	public class PathGenerator {

		public Doorway generateDoor(Room generateFrom, Direction out) {
			// Intermediary method. When asking if a door should be made, always
			// says yes, and give a new door.
			// TODO: Implement properly to guarantee an interesting, but wholly
			// connected, map.
			// TODO: If a door already exists between the two rooms, return that
			// one, rather than giving a new doorway
			return new Doorway(generateFrom, out);
		}

	}

	/**
	 * Generates a biome map based on noise values for multiple aspects.
	 * 
	 * @author geekman9097
	 *
	 */
	public class BiomeGenerator {
		private final FileReader dataReader; // reads the data off the file.
		private final int complexity; // how many bases of generation to use.
		private final int[] precision; // the highest value found for generation
										// for each generator

		private final OpenSimplexNoise[] noiseGenerators; // the group of noise
															// planes to draw
															// our data from
		private final Map<String, List<String>> biomeGroups; // a list of the
																// calls for
																// biomes that
																// can be
																// generated,
																// for some data
																// values
		private final Random biomeGrabber; // a random generator for selecting
											// which biome from the possible
											// group to use.
		private List<String> biomesToSave = new LinkedList<>();

		public BiomeGenerator(File readFile) {
			//TODO: replace with parser call
			// set up data storage
			biomeGroups = new HashMap<>();
			// initialize the random with a new seed from the map generator
			biomeGrabber = new Random(getNewSeed());

			// create a data reader
			dataReader = new FileReader(readFile);

			System.out.println(dataReader.getLines().toString());

			// find out how many generators we need to use
			complexity = dataReader.loadFirstLine().getLineAsData().length;

			// Load data into collections
			noiseGenerators = new OpenSimplexNoise[complexity];
			for (int i = 0; i < complexity; i++) {
				noiseGenerators[i] = new OpenSimplexNoise(getNewSeed());
			}
			precision = loadBiomes();
		}
		
		public BiomeGenerator(int complexity, int[] precision, Map<String, List<String>> data) {
			//TODO: call this from parser
			dataReader = null;
			
			this.complexity = complexity;
			this.precision = precision;
			biomeGroups = data;
			
			noiseGenerators = new OpenSimplexNoise[complexity];
			for (int i = 0; i < complexity; i++) {
				noiseGenerators[i] = new OpenSimplexNoise(getNewSeed());
			}
			biomeGrabber = new Random(getNewSeed());
		}

		private int[] loadBiomes() {
			//TODO: use Biome Parser instead of handling locally.
			int[] tempPrecision = new int[complexity];
			String[] data = dataReader.loadNextLine().getLineAsValues();
			
			while (!data[1].contains(FileReader.END_OF_FILE)) {
				String name = data[0];
				int[] values = new int[complexity];
				for (int i = 0; i < complexity; i++) {
					values[i] = Integer.parseInt(data[i + 1]);
					if (values[i] > tempPrecision[i])
						tempPrecision[i] = values[i];
				}
				addBiome(values, name);

				data = dataReader.loadNextLine().getLineAsValues();
			}
			return tempPrecision;
		}

		private void addBiome(int[] values, String biomeName) {
			String key = valuesToKey(values);
			biomeGroups.putIfAbsent(key, new ArrayList<>());
			biomeGroups.get(key).add(biomeName);
			
			biomesToSave.add(biomeName);
		}

		private String valuesToKey(int[] values) {
			StringBuilder result = new StringBuilder("");
			for (int i : values) {
				result.append(i);
			}
			return result.toString();
		}

		private int[] getValuesAt(int x, int z) {
			int[] result = new int[complexity];
			for (int i = 0; i < complexity; i++) {
				result[i] = noiseToKey(noiseGenerators[i].eval(x, z), i);
			}
			return result;
		}

		/**
		 * converts a 2D noise value (within
		 * <code>{-sqrt(1/2),sqrt(1/2)}</code>) to a key value (within
		 * <code>{0, precision[generatorNumber]}</code>)
		 * 
		 * @param noise
		 * @param generatorNumber
		 * @return
		 */
		private int noiseToKey(double noise, int generatorNumber) {
			int maxVal = precision[generatorNumber];
			// normalize the value from 0 to 1
			double dataVal = (noise - Math.sqrt(1 / 2)) / (Math.sqrt(2));
			// convert the data val to a non-rounded key
			double keyVal = dataVal * maxVal;
			// round to nearest integer
			int key = (int) (keyVal + 0.5);
			return key;
		}

		private List<String> getBiomeNames(int[] values) {
			String key = valuesToKey(values);
			return biomeGroups.get(key);
		}

		private String getRandomBiomeName(List<String> biomes) {
			int index = biomeGrabber.nextInt(biomes.size());
			return biomes.get(index);
		}

		public String getBiomeAt(int x, int z) {
			return getRandomBiomeName(getBiomeNames(getValuesAt(x, z)));
		}
		
		public List<String> getSaveableBiomes() {
			return biomesToSave;
		}

	}
}
