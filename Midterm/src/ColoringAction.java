public class ColoringAction extends Action {
    int changedNode;
    int changedColor;

    public ColoringAction(int changedNode, int changedColor) {
        this.changedNode = changedNode;
        this.changedColor = changedColor;
    }
}
