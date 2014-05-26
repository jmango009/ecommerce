/**
 * 
 */
package com.ecommerce.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author richard
 * 
 */
public abstract class UploadFile extends BaseEntity {

	private static final long serialVersionUID = -3351984153799193720L;
	
	protected MultipartFile file;

	public MultipartFile getFile() {
		return this.file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
