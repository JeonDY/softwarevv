package GUI;

import javax.swing.*;
import java.awt.*;

public class TimeKeeping_Pane extends JPanel{
	//Segment JPanel
	private JPanel secondSegBody;
	private JPanel firstSegBody;

	//Image Label owned by Segment Panel
	private JLabel secondSegs[]; // Second Segment. 10 components
	private JLabel firstSegs[]; // First Segment. 8 components
	private JLabel clockLabel; //clock icon

	private JLabel cursorLabel[]; //arrow icon

	//text label owned by bodyPanel
	private JLabel title; //mode name
	private JLabel meridiemLabel; //AM PM text label
	private JLabel dowLabel; // day of week text label

	public TimeKeeping_Pane() {

		setVisible(true);
		setSize(400,240);
		setLocation(45, 30);
		setBackground(Color.white);
		setLayout(null);

		//initialize & draw segment JPanel
		secondSegBody = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D)g;
				RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g2.setRenderingHints(qualityHints);
				g2.setStroke(new BasicStroke(3));

				g2.drawRoundRect(2, 2, this.getWidth()-4, this.getHeight()-4, 20, 20);

			}
		};
		firstSegBody = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D)g;
				RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g2.setRenderingHints(qualityHints);
				g2.setStroke(new BasicStroke(3));

				g2.drawRoundRect(2, 2, this.getWidth()-4, this.getHeight()-4, 20, 20);

			}
		};

		//initialize second segment labels
		secondSegs = new JLabel[10];
		for(int i=0; i<10; i++) secondSegs[i] = new JLabel();

		//initialize first segment labels
		firstSegs = new JLabel[8];
		for(int i=0; i<8; i++) firstSegs[i] = new JLabel();

		//Cursor Icon
		cursorLabel = new JLabel[6];
		for(int i=0; i<6; i++) cursorLabel[i] = new JLabel();
		
		//Clock Icon
		clockLabel = new JLabel();

		//AMPM & Day of week Label
		meridiemLabel = new JLabel();
		dowLabel = new JLabel();

		title = new JLabel("TIME KEEPING");

		//second seg panel Info
		secondSegBody.setSize(205, 55);
		secondSegBody.setLocation(25,35);
		secondSegBody.setBackground(Color.white);
		secondSegBody.setLayout(new GridLayout(1, 10, 0, 0));
		secondSegBody.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Internal margin

		//first seg panel info
		firstSegBody.setSize(360, 95);
		firstSegBody.setLocation(20, 120);
		firstSegBody.setBackground(Color.white);
		firstSegBody.setLayout(new GridLayout(1, 8, 0, 0));
		firstSegBody.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Internal margin

		//Cursor
		for(int i=0; i<2; i++) cursorLabel[i].setBounds(secondSegBody.getX() + 43 + (i*72), secondSegBody.getY() - 10, 10, 10);
		cursorLabel[2].setBounds(secondSegBody.getX() + 169, secondSegBody.getY() - 10, 10, 10);
		for(int i=0; i<3; i++) cursorLabel[i+3].setBounds(firstSegBody.getX() + 45 + (i*126), firstSegBody.getY() - 20, 20, 20);

		//clock image component info
		clockLabel.setSize(50, 50);
		clockLabel.setLocation(this.getWidth() - 80, secondSegBody.getLocation().y - 2);
		
		//AM/PM & dayofweek component info
		meridiemLabel.setFont(new Font("Open Sans", Font.BOLD, 15));
		meridiemLabel.setHorizontalAlignment(SwingConstants.LEFT);
		meridiemLabel.setBounds((secondSegBody.getLocation().x+secondSegBody.getWidth()) + 5, secondSegBody.getLocation().y + 5, 41, 21);
		dowLabel.setFont(new Font("Open Sans", Font.BOLD, 13));
		dowLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dowLabel.setBounds((secondSegBody.getLocation().x+secondSegBody.getWidth()) + 5, secondSegBody.getLocation().y + 25, 41, 21);

		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0, 10, this.getWidth(), 20);

		//Component adding
		add(firstSegBody);
		add(secondSegBody);
		add(clockLabel);
		add(title);
		add(meridiemLabel);
		add(dowLabel);
		for(int i=0; i<6; i++) add(cursorLabel[i]);

		for(int i=0; i<10; i++) secondSegBody.add(secondSegs[i]);

		for(int i=0; i<8; i++) firstSegBody.add(firstSegs[i]);

		this.repaint();
		this.revalidate();
	}
	
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        g2.setStroke(new BasicStroke(3));

        g2.drawRoundRect(2, 2, this.getWidth()-5, this.getHeight()-5, 40, 40);

    }

    //getters
	public JPanel getSecondSegBody() {
		return secondSegBody;
	}

	public JPanel getFirstSegBody() {
		return firstSegBody;
	}

	public JLabel[] getSecondSegs() {
		return secondSegs;
	}

	public JLabel[] getFirstSegs() {
		return firstSegs;
	}

	public JLabel getClockLabel() {
		return clockLabel;
	}

	public JLabel getMeridiemLabel() {
		return meridiemLabel;
	}

	public JLabel getDowLabel() {
		return dowLabel;
	}

	public JLabel getTitle() { return title; }

	public JLabel[] getCursorLabel() { return cursorLabel; }

}
