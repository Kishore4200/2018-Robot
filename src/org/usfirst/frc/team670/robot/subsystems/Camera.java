package org.usfirst.frc.team670.robot.subsystems;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {

	private Mat source;
	private boolean capture = false, stream = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Camera()
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
	            
	            while(!Thread.interrupted()) {
	            	if(capture)
	            	{
	            		cvSink.grabFrame(source);
	            		if(stream)
	            			outputStream.putFrame(source);
	            	}
	            }
		      }
		}.start();
	}

	//Get angle to powercube
	/*public Vector getVector()
	{
		Point targetPoint = Robot.oi.targetPoint;
		Rect r = getPowerCube(source, Robot.oi.lowerHSV, Robot.oi.upperHSV);
		double angle = getAngle(r, new Point(640, 360));
		Point currentBox = new Point(r.x+(r.width/2),r.y+(r.height/2));
		double magnitude = Math.sqrt(Math.pow((currentBox.getX()-targetPoint.getX()),2) + Math.pow((currentBox.getY()-targetPoint.getY()),2));
		Vector v = new Vector(magnitude, angle);
		return v;
	}*/
	
	public double getAngle()
	{
		Rect r = getPowerCube(source, Robot.oi.lowerHSV, Robot.oi.upperHSV);
		double angle = getAngle(r, Robot.oi.targetPoint);
		return angle;
	}
	
	public void enableCapture()
	{
		capture = true;
	}
	
	public void endCapture()
	{
		capture = false;
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
}

