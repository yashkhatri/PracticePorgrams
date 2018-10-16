package test1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


/**
 * The class <code>DiskSpace</> contain methods related to available disk space and possiblility of
 * writing data on disk.
 *  
 * @author Yash Khatri
 */
public class DiskSpace {

	/**
	 * @param blockSize 
	 * 		 The size of the block of memory on disk.
	 * @param fileSize
	 * 		 The size of the file that is to be stored on disk.
	 * @param occupiedSectors
	 *       The sectors in the block that are already occupied by some other data.
	 * @return boolean
	 * 		 Returns true, if the file can be stored in the single chunk in the block.
	 * 		 Returns false, otherwise. 
	 */
	public static boolean isWritable(int blockSize, int fileSize, Set<Integer> occupiedSectors) {

		//The constraints.
		if (blockSize > 1000000 || blockSize < 1 || fileSize > blockSize || fileSize < 1) {
			return false;
		}

		if ((occupiedSectors != null && occupiedSectors.size() == 0) && (fileSize <= blockSize)) {
			return true;
		}

		//Sorting the occupied sectors based on their indexes.
		TreeSet<Integer> sortedOccupiedSectors = new TreeSet<Integer>();
		sortedOccupiedSectors.addAll(occupiedSectors);

		Integer blockStartIndex = 1;
		Integer availableChunkSize;

		//Checking the availableChunkSize between blockStartIndex i.e. 1 - firstOccupiedSector
		//and between OccupiedSectors.
		for (Integer occupiedSector : sortedOccupiedSectors) {

			if(sortedOccupiedSectors.contains(blockStartIndex)) {
				availableChunkSize = occupiedSector - blockStartIndex - 1;
			} else {
				//If the blockStartIndex i.e. 1 is not occupied (possible only when executing the loop first time.)
				availableChunkSize = occupiedSector - blockStartIndex;
			}
			
			
			if (availableChunkSize >= fileSize) {
				return true;
			}

			blockStartIndex = occupiedSector;

		}

		//blockSize here is also equal to the last block index.
		//Checking the available chunk size between the last occupied sector and end index of block. 
		if ((blockSize - getLastOccupiedSector(sortedOccupiedSectors)) >= fileSize) {
			return true;
		}

		return false;
	}

	
	/**
	 * This method is a utility method, used for getting the last element of a collection.
	 * (Should be in utility class, ideally)
	 * @param collection. 
	 * @return the last element of the collection.
	 */
	private static Integer getLastOccupiedSector(final Collection<Integer> c) {
		final Iterator<Integer> itr = c.iterator();
		Integer lastElement = (Integer) itr.next();
		while (itr.hasNext()) {
			lastElement = (Integer) itr.next();
		}
		return lastElement;
	}
}