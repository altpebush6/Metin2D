package ai;

/**
 * <p>
 * This Class set the Nodes
 * </p>
 */

public class Node {
    
    /**
     * <p>
     * This indicates the parent node
     * </p>
     */
    public Node parent;
    /**
     * <p>
     *  Indicates columns
     * </p>
     */
    public int col;
    /**
     * <p>
     *  Indicates rows
     * </p>
     */
    public int row;
    
    /**
     * <p>
     *  This stores gCosts of the given tiles
     * </p>
    */
    public int gCost;
    /**
     * <p>
     *  This stores hCosts of the given tiles
     * </p>
    */
    public int hCost;
    /**
     * <p>
     *  This stores fCosts of the given tiles
     * </p>
    */
    public int fCost;
    /**
     * <p>
     *  Controls whether tile solid or not
     * </p>
    */
    public boolean solid;
    /**
     * <p>
     *  Controls is node open
     * </p>
    */
    public boolean open;
    /**
     * <p>
     *  Controls is node open
     * </p>
    */
    public boolean checked;
    
    /**
     * <p>
     * This is constructor
     * </p>
     * 
     * @param col refers column size
     * @param row refers row size
     * @since 1.0
     */
    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
