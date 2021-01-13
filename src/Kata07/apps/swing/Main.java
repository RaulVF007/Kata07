package Kata07.apps.swing;

import Kata07.control.BlockPresenter;
import Kata07.control.Command;
import Kata07.control.DownCommand;
import Kata07.control.LeftCommand;
import Kata07.control.RightCommand;
import Kata07.control.UpCommand;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Kata07.model.Block;
import Kata07.view.BlockDisplay;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JMenuBar;

public class Main extends JFrame {
    private final static int BLOCK_SIZE = 100;
    private final BlockPresenter blockPresenter;
    private BlockDisplay blockDisplay;
    private Map<String,Command> commands = new HashMap<>();
    
    public static void main(String[] args) {
        new Main().execute();
    }
    
    public Main() {
        this.setTitle("Block shifter");
        this.setSize(700, 750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        
        Block block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands.put("left", new LeftCommand(block));
        this.commands.put("right", new RightCommand(block));
        this.commands.put("up", new UpCommand(block));
        this.commands.put("down", new DownCommand(block));
    }

    private void execute() {
        this.setVisible(true);
    }

    private BlockPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel(Block.MAX, BLOCK_SIZE);
        this.blockDisplay = blockPanel;
        return blockPanel;
    }
    
    private JMenuBar toolbar(){
        JMenuBar menu = new JMenuBar();
        menu.add(button("left"));
        menu.add(button("right"));
        menu.add(button("up"));
        menu.add(button("down"));
        return menu;
    }
    
    private JButton button(String name){
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        return button;
    }   
}