package edu.xaut.animalsnapshot;

import edu.xaut.model.Snapshot;

import java.util.LinkedHashMap;

/**
 * Created by anyang on 2016/9/10.
 */
public class AnimalSnapshot {

    public String getSnapShot(String historyData, String id) {
        LinkedHashMap<String, Snapshot> historySnapshots = null;
        try {
            historySnapshots = convertToObjectFormat(historyData);
            Snapshot snapshotInSpecifiedTime = historySnapshots.get(id);
            return printSnapshot(snapshotInSpecifiedTime);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private LinkedHashMap<String, Snapshot> convertToObjectFormat(String historyData) throws Exception {
        LinkedHashMap<String, Snapshot> originalCoordinates = formatWithOriginalCoordinates(historyData);
        return formatWithNewestCoordinates(originalCoordinates);
    }

    private LinkedHashMap<String, Snapshot> formatWithOriginalCoordinates(String historyData)
            throws Exception {
        DataFormatWithOriginalCoordinates dataFormat = new DataFormatWithOriginalCoordinates();
        return dataFormat.format(historyData);
    }

    private LinkedHashMap<String, Snapshot> formatWithNewestCoordinates(
            LinkedHashMap<String, Snapshot> originalCoordinates) throws Exception {
        DataFormatWithNewestCoordinates dataFormat = new DataFormatWithNewestCoordinates();
        return dataFormat.format(originalCoordinates);
    }

    private String printSnapshot(Snapshot snapshot) {
        SnapshotPrinter snapshotPrinter = new SnapshotPrinter();
        return snapshotPrinter.printMultipleAnimalCoordinates(snapshot);
    }
}
