package com.gosuccour.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathPhoto = getPath(filename);
		log.info("pathPhoto" + pathPhoto);
		Resource resource = null;

		resource = new UrlResource(pathPhoto.toUri());
		if (!resource.exists() || !resource.isReadable()) {
			throw new RuntimeException("Error¡¡ can't upload image " + pathPhoto.toString());
		}

		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		// guarda la imagenes en static, no viable ya que siempre se tiene que refrescar
		// Path directoryResources = Paths.get("src//main//resources//static/uploads");
		// String rootPath = directoryResources.toFile().getAbsolutePath();
		// guardando en un directorio externo
		// String rootPath="//Users//GraceToa//Documents//uploadsUdemy";

		// para que el nombre de la img sea unico
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

		// guardar en directorio absuluto y externo en raiz proyecto
		Path rootPath = getPath(uniqueFilename);

		log.info("rootPath:" + rootPath);// path relativo al proyecto

		// 1 opcion
		// byte[]bytes = photo.getBytes();
		// Path rootComplet = Paths.get(rootPath + "//" + photo.getOriginalFilename());
		// Files.write(rootComplet, bytes);
		// 2 opcion
		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;// retorna nombre unico de archivo
	}

	@Override
	public boolean delete(String filename) {
		// para eliminar photo de uploads
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();

		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete()) {
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();

	}

	/*
	 * borra la carpeta uploads si existe asi cada vez que se arranca la app se crea
	 * carpeta
	 */
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
	}

	/* crea la carpeta uploads */
	@Override
	public void init() throws IOException {
		Files.createDirectories(Paths.get(UPLOADS_FOLDER));
	}
}
