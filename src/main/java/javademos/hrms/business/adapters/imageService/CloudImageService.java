package javademos.hrms.business.adapters.imageService;

import java.io.IOException;
import java.io.InputStream;

public interface CloudImageService {
	
	/**
	 * @return image path
	 */
	String upload(InputStream stream) throws IOException;
}
