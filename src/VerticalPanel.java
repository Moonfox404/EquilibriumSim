import javax.swing.*;

public class VerticalPanel extends JPanel{
    private final MenuPanel solutionMenu;
    private final MenuPanel titrantMenu;
    private final JButton runButton = new JButton("Run");

    public VerticalPanel(int width, int height){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(width, height);

        String[] sOptions = {"Strong Acid", "Strong Base", "Weak Acid", "Weak Base"};
        solutionMenu = new MenuPanel("Solution", sOptions, false, width, height/3);

        String[] tOptions = {"N/A", "Strong Acid", "Strong Base", "Weak Acid", "Weak Base"};
        titrantMenu = new MenuPanel("Titrant", tOptions, true, width, height/3);

        this.add(solutionMenu);
        this.add(titrantMenu);
        this.add(runButton);
    }

    public MenuPanel getSolutionMenu(){
        return solutionMenu;
    }

    public MenuPanel getTitrantMenu() {
        return titrantMenu;
    }

    public JButton getRunButton(){
        return runButton;
    }
}
