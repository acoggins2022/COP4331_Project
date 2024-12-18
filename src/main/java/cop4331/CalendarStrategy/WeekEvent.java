/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.CalendarStrategy;

import cop4331.gui.EventDetailsController;
import cop4331.EventComposite.Event;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JFrame;

/**
 * Class utilized to display events happening this week
 * @invariant layouts will not change 
 * @author cashhollister
 */
public class WeekEvent extends JPanel { 
    private Color color;
    
    /**
     * Constructor utilized to create week day event view
     * @preconditons existing JFrame and event
     * @postconditiond Event display created for event happening this week 
     * @param event
     * @param frame 
     */
    public WeekEvent(Event event, JFrame frame) {
        this.color = Color.BLACK;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       
        // generate labels for each detail
        JLabel title = new JLabel(event.getTitle());
        JLabel course = new JLabel(event.getCourse());
        JLabel time = new JLabel(event.getTime());
        
        // generate details button
        JButton detailsButton = new JButton("details");
        
        // add detail labels
        add(title);
        add(course);
        add(time);
        
        // handle complete status
        if (event.getComplete()) {
            color = Color.GREEN;
            JLabel complete = new JLabel("Completed");
            add(complete);
        } else {
            JLabel complete = new JLabel("Not Completed");
            add(complete);
        }
        
        // handle conflict status
        if (event.getConflict()) {
            color = Color.RED;
            JLabel conflict = new JLabel("Alert: Conflict with event");
            add(conflict);
        } else {
            JLabel conflict = new JLabel("No Conflicts with event");
            add(conflict);
        }
        
        // add details button
        add(detailsButton);
        
        // add space to bottom
        add(Box.createVerticalStrut(20));
        
        //set color based on conditions of details
        for (Component component : this.getComponents()) {
            component.setForeground(color);
        }
        
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EventDetailsController(event, frame);
            }
        });
    }
    /**
     * Public method utilized to pain the grid component
     * @preconditions none
     * @postconditions grid is painted
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Generate border 
        g2.setColor(this.color);
        g2.draw(new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1));  
    }
}