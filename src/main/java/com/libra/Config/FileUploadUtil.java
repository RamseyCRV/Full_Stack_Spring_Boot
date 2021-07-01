package com.libra.Config;
import java.io.*;
import java.nio.file.*;
import java.util.logging.Logger;

import org.springframework.web.multipart.MultipartFile;

public final class FileUploadUtil {

    private static final Logger LOG = Logger.getLogger(String.valueOf(FileUploadUtil.class));

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static void deleteImage(final String imageName){
        try
        {
            Files.deleteIfExists(Paths.get(LibraConstants.ConfigConstants.AVATAR_USER_PATH + imageName));
        }
        catch(NoSuchFileException e)
        {
            LOG.info("No such file/directory exists when try to delete " + imageName);
        }
        catch(DirectoryNotEmptyException e)
        {
            LOG.info("Directory is not empty when try to delete " + imageName);
        }
        catch(IOException e)
        {
            LOG.info("Invalid permissions when try to delete " + imageName);
        }

    }

}