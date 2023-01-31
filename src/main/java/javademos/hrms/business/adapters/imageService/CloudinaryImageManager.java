package javademos.hrms.business.adapters.imageService;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryImageManager implements CloudImageService {

	private Cloudinary cloudinary;

	@Autowired
	public CloudinaryImageManager(Cloudinary cloudinary) {

		this.cloudinary = cloudinary;
	}

	@Override
	public String upload(InputStream stream) throws IOException {

		var map = this.cloudinary.uploader().uploadLarge(stream, ObjectUtils.emptyMap());
		String publicId = map.get("public_id").toString();
		return this.cloudinary.url().generate(publicId);
	}
}
