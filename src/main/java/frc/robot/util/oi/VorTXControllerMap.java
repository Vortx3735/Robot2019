package frc.robot.util.oi;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public abstract class VorTXControllerMap {

    public VorTXControllerButton a,b,x,y,lb,rb,back,start,ls,rs,lt,rt;
    public VorTXControllerButton pov0,pov45,pov90,pov135,pov180,pov225,pov270,pov315;
    
    public VorTXControllerMap() {
        a = new VorTXControllerButton();
        b = new VorTXControllerButton();
        x = new VorTXControllerButton();
        y = new VorTXControllerButton();
        start = new VorTXControllerButton();
        back = new VorTXControllerButton();
        lb = new VorTXControllerButton();
        rb = new VorTXControllerButton();
        pov0 = new VorTXControllerButton();
        pov90 = new VorTXControllerButton();
        pov180 = new VorTXControllerButton();
        pov270 = new VorTXControllerButton();
    }
}