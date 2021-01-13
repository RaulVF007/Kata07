package Kata07.control;

import Kata07.model.Block;

public class DownCommand implements Command {
    private final Block block;

    public DownCommand(Block block){
        this.block = block;
    }
    
    @Override
    public void execute() {
        block.down();
    }
    
}