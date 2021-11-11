public class CellFactory {
    // Factory design pattern to create cells
    public static Cell getCell(String cellType){
        if(cellType == null){
            return null;
        }
        if(cellType.equalsIgnoreCase("NEXUS")){
            return new NexusCell();

        } else if(cellType.equalsIgnoreCase("INACCESSIBLE")){
            return new InaccessibleCell();

        } else if(cellType.equalsIgnoreCase("BUSH")){
            return new BushCell();

        } else if(cellType.equalsIgnoreCase("CAVE")) {
            return new CaveCell();

        }else if(cellType.equalsIgnoreCase("KOULOU")) {
            return new KoulouCell();
        }else if(cellType.equalsIgnoreCase("PLAIN")) {
            return new PlainCell();
        }

        return null;
    }
}
