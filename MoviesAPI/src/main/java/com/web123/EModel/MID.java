package com.web123.EModel;

import java.io.Serializable;

public class MID implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id ;
    public MID() {
    }
    public MID(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
