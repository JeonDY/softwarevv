package GUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class TimeKeeping_Pane extends JPanel implements Runnable{

	private LocalDateTime currentTime;

	//Segment JPanel
	private JPanel secondSegBody;
	private JPanel firstSegBody;

	//Image Label owned by Segment Panel
	private JLabel secondSegs[]; // Second Segment. 10 components
	private JLabel firstSegs[]; // First Segment. 8 components
	private JLabel clockLabel; //clock icon

	//text label owned by bodyPanel
	private JLabel meridiemLabel; //AM PM text label
	private JLabel dowLabel; // day of week text label

	//ImageIcon (will put into JLabel)
	private ImageIcon seg14DeadBigImg; //grey big seg image
	private ImageIcon seg14DeadImg; //grey seg image
	private ImageIcon numBigImgs[]; //big num image 0 ~ 9
	private ImageIcon numImgs[]; //small num image 0 ~ 9
	private ImageIcon colonBigImg; //big colon image
	private ImageIcon colonImg; //small colon image
	private ImageIcon clockImg; //black clock image
	private ImageIcon clockDeadImg; //grey clock image


	public TimeKeeping_Pane(LocalDateTime currentTime) {

		setVisible(true);
		setSize(400,240);
		setLocation(45, 30);
		setBackground(Color.white);
		setLayout(null);
		this.currentTime = currentTime;

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

		//load Images from resource folder
		seg14DeadBigImg = new ImageIcon(this.getClass().getResource(ImageDir.SegDead14Big_dir));
		seg14DeadImg = new ImageIcon(this.getClass().getResource(ImageDir.SegDead14_dir));
		colonImg = new ImageIcon(this.getClass().getResource(ImageDir.colon_dir));
		colonBigImg = new ImageIcon(this.getClass().getResource(ImageDir.colonBig_dir));
		clockImg = new ImageIcon(this.getClass().getResource(ImageDir.clock_dir));
		clockDeadImg = new ImageIcon(this.getClass().getResource(ImageDir.clockDead_dir));

		numBigImgs = new ImageIcon[10]; // first segment's numbers
		for(int i=0; i<10; i++){
			numBigImgs[i] = new ImageIcon(this.getClass().getResource(ImageDir.numBigdirs[i]));
		}

		numImgs = new ImageIcon[10]; // second segment's numbers
		for(int i=0; i<10; i++){
			numImgs[i] = new ImageIcon(this.getClass().getResource(ImageDir.numdirs[i]));
		}


		//set default images to second Segment
		secondSegs = new JLabel[10];
		for(int i=0; i<10; i++){
			if(!(i == 4 || i == 7)) secondSegs[i] = new JLabel(numImgs[0]);
			else secondSegs[i] = new JLabel(colonImg);
		}
	
		//set default images to first Segment
		firstSegs = new JLabel[8];
		for(int i=0; i<8; i++){
			if(!(i==2 || i==5)) firstSegs[i] = new JLabel(numBigImgs[0]);
			else firstSegs[i] = new JLabel(colonBigImg);
		}
		
		//Clock Icon
		clockLabel = new JLabel(clockDeadImg);
		
		meridiemLabel = new JLabel("AM");
		dowLabel = new JLabel("MON");

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

		//Component adding
		add(firstSegBody);
		add(secondSegBody);
		add(clockLabel);
		add(meridiemLabel);
		add(dowLabel);

		for(int i=0; i<10; i++){
			secondSegBody.add(secondSegs[i]);
		}

		for(int i=0; i<8; i++){
			firstSegBody.add(firstSegs[i]);
		}

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

	public LocalDateTime getCurrentTime() {
		return currentTime;
	}

	public ImageIcon getSeg14DeadBigImg() {
		return seg14DeadBigImg;
	}

	public ImageIcon getSeg14DeadImg() {
		return seg14DeadImg;
	}

	public ImageIcon getColonImg() {
		return colonImg;
	}

	public ImageIcon getColonBigImg() {
		return colonBigImg;
	}

	public ImageIcon getClockImg() {
		return clockImg;
	}

	public ImageIcon getClockDeadImg() {
		return clockDeadImg;
	}

	public ImageIcon[] getNumBigImgs() {
		return numBigImgs;
	}

	public ImageIcon[] getNumImgs() {
		return numImgs;
	}

	//setters
	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}

	public void setSecondSegs(JLabel[] secondSegs) {
		this.secondSegs = secondSegs;
	}

	public void setFirstSegs(JLabel[] firstSegs) {
		this.firstSegs = firstSegs;
	}

	public void setClockLabel(JLabel clockLabel) {
		this.clockLabel = clockLabel;
	}

	public void setMeridiemLabel(JLabel meridiemLabel) {
		this.meridiemLabel = meridiemLabel;
	}

	public void setDowLabel(JLabel dowLabel) {
		this.dowLabel = dowLabel;
	}

	@Override
	public void run() {

	}
}
