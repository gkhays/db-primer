package org.gkh.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accesspoint")
public class AccessPoint {

	Long id;
    String essid;
    String bssid;
    String vendor;
    Integer channel;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "essid")
	public String getEssid() {
		return essid;
	}
	public void setEssid(String essid) {
		this.essid = essid;
	}
	
	@Column(name = "bssid")
	public String getBssid() {
		return bssid;
	}
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	
	@Column(name = "vendor")
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	@Column(name = "channel")
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
}