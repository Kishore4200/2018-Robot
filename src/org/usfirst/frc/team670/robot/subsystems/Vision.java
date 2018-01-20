package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

@SuppressWarnings("deprecation")
public class Vision extends Subsystem {
	
	private NetworkTable vision;
	
	public Vision()
	{
		vision = NetworkTable.getTable("vision");
	}
	
	public double getAngle()
	{
		if(vision == null)
		{
			vision = NetworkTable.getTable("vision");
			return -1;
		}
		else
			return vision.getNumber("angle", -1);
	}
	
	public double getWidth()
	{
		if(vision == null)
		{
			vision = NetworkTable.getTable("vision");
			return -1;
		}
		else
			return vision.getNumber("width", -1);
	}

	public double getHeight()
	{
		if(vision == null)
		{
			vision = NetworkTable.getTable("vision");
			return -1;
		}
		else
			return vision.getNumber("height", -1);
	}
	
	public void initDefaultCommand() 
	{
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


/*
 * 	private Mat source;
	private boolean capture = false, stream = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Vision()
	{
		source = new Mat();
		
		new Thread() 
		{
		      public void run()
		      {
		    	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	            camera.setResolution(640, 480);
	            
	            CvSink cvSink = CameraServer.getInstance().getVideo();
	            CvSource outputStream = CameraServer.getInstance().putVideo("PowerCube", 640, 480);
	            
	            while(!Thread.interrupted()) 
	            {
	            		cvSink.grabFrame(source);
	            		//Rect boundingBox = getPowerCube(source, Robot.oi.lowerHSV, Robot.oi.upperHSV);
	        			//double angle = getAngle(boundingBox, Robot.oi.targetPoint);
	        			//SmartDashboard.putString("Angle to power cube", ""+angle);
	            		//Imgproc.rectangle(source, new org.opencv.core.Point(boundingBox.x, boundingBox.y), new org.opencv.core.Point(boundingBox.x+boundingBox.width, boundingBox.y+boundingBox.height), new Scalar(255, 0, 0));
	            		outputStream.putFrame(source);
	            }
		      }
			}.start();
	}
	
	public double getAngle()
	{
		Rect r = getPowerCube(source, Robot.oi.lowerHSV, Robot.oi.upperHSV);
		double angle = getAngle(r, Robot.oi.targetPoint);
		SmartDashboard.putString("Angle to Power Cube:", angle + "");
		return angle;
	}

	private double getAngle(Rect r, Point targetPoint) {
		//Camera is 1280x720, view angle is 61 degrees horizontal
		//1280/61 = 0.04765625
		double degreesPerPixel = 0.04765625; //Should be a final field (Degrees per pixel for the camera, see above)
		double targetPointX = targetPoint.getX(); //Should be a final field (The midpoint x value of the camera, see above)
		double rads;
		double rectMidpointX = r.x+(r.width/2);

		rads = degreesPerPixel * (targetPointX - rectMidpointX);
		return rads;
	}
	
	public Rect getPowerCube(Mat matrix, Scalar lowerHSV, Scalar upperHSV)
	{
		Mat input = matrix.clone(), mHier = new Mat();
		Imgproc.cvtColor(input, input, Imgproc.COLOR_BGR2HSV);
		Imgproc.GaussianBlur(input, input, new Size(5, 5) ,5 ,5);
		//Detect up-->down, to find color of Scale
		ArrayList<Rect> boxes = new ArrayList<Rect>();
		
		Core.inRange(input, lowerHSV, upperHSV, input);
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(input, contours, mHier, Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		Rect box = new Rect(0,0,0,0);
		MatOfPoint p = null;
		for (int i = 0; i < contours.size(); i++)
		{
			MatOfPoint2f contour2f = new
					MatOfPoint2f(contours.get(i).toArray());
			double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
			Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
			MatOfPoint points = new MatOfPoint( approxCurve.toArray() );
			Rect r = Imgproc.boundingRect(points);			
			if(r.area()>=box.area())
			{
				box = r;
				p = points;
			}
		}
		return box;
	}
	  
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}*/