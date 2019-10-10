package model.logic;

public class Viaje
{
	private double sourceid;
	private double dstid;
	private double dow;
	private double mean_travel_time;
	private double standard_deviation_travel_time;
	private int trimestre;
	
	Viaje(int _trimestre,String _sourceid, String _dstid, String _dow, String _mean_travel_time, String _standard_deviation_travel_time)
	{
		trimestre=_trimestre;
		setSourceid(Double.parseDouble(_sourceid));
		setDstid(Double.parseDouble(_dstid));
		setDow(Double.parseDouble(_dow));
		setMean_travel_time(Double.parseDouble(_mean_travel_time));
		setStandard_deviation_travel_time(Double.parseDouble(_standard_deviation_travel_time));
	}

	@SuppressWarnings("unused")
	private void setTrimestre(int n) {
		trimestre=n;
		
	}
	public int getTrimestre() {
		return trimestre;
	}
	public double getSourceid() {
		return sourceid;
	}

	public void setSourceid(double sourceid) {
		this.sourceid = sourceid;
	}

	public double getDstid() {
		return dstid;
	}

	public void setDstid(double dstid) {
		this.dstid = dstid;
	}

	public double getDow() {
		return dow;
	}

	public void setDow(double dow) {
		this.dow = dow;
	}

	public double getMean_travel_time() {
		return mean_travel_time;
	}

	public void setMean_travel_time(double mean_travel_time) {
		this.mean_travel_time = mean_travel_time;
	}

	public double getStandard_deviation_travel_time() {
		return standard_deviation_travel_time;
	}

	public void setStandard_deviation_travel_time(double standard_deviation_travel_time) {
		this.standard_deviation_travel_time = standard_deviation_travel_time;
	}

}