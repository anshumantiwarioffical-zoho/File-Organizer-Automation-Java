// File Organizer Automation Tool
// This program automatically organizes files in a given folder
// into different subfolders based on file type.
//
// Developed using Core Java only
// Focus: File Handling, Logic, Automation

import java.io.File;  // Import File class to work with files and folders

public class FileOrganizer {

    public static void main(String[] args) {

        // STEP 1: Provide the folder path which needs to be organized
        // Example: Downloads folder path
        // IMPORTANT: Change this path according to your system
        String folderPath = "D:\\TestFolder";

        // Create a File object representing the main folder
        File mainFolder = new File(folderPath);

        // STEP 2: Validate whether the given path exists and is a directory
        if (!mainFolder.exists() || !mainFolder.isDirectory()) {
            System.out.println("Invalid folder path. Please check the path.");
            return; // Stop execution if folder is invalid
        }

        // STEP 3: Create category folders inside the main folder
        // These folders will store files based on type
        File imageFolder = new File(mainFolder, "Images");
        File documentFolder = new File(mainFolder, "Documents");
        File videoFolder = new File(mainFolder, "Videos");
        File otherFolder = new File(mainFolder, "Others");

        // mkdir() creates the folder if it does not already exist
        imageFolder.mkdir();
        documentFolder.mkdir();
        videoFolder.mkdir();
        otherFolder.mkdir();

        // STEP 4: Get list of all files present in the main folder
        File[] files = mainFolder.listFiles();

        // If folder is empty or inaccessible
        if (files == null) {
            System.out.println("No files found in the folder.");
            return;
        }

        // STEP 5: Loop through each file in the folder
        for (File file : files) {

            // Process only files, ignore subfolders
            if (file.isFile()) {

                // Convert file name to lowercase for easy comparison
                String fileName = file.getName().toLowerCase();

                // STEP 6: Check file extension and move accordingly

                // Image files
                if (fileName.endsWith(".jpg") ||
                    fileName.endsWith(".jpeg") ||
                    fileName.endsWith(".png")) {

                    moveFile(file, imageFolder);
                }

                // Document files
                else if (fileName.endsWith(".pdf") ||
                         fileName.endsWith(".docx") ||
                         fileName.endsWith(".txt")) {

                    moveFile(file, documentFolder);
                }

                // Video files
                else if (fileName.endsWith(".mp4") ||
                         fileName.endsWith(".avi")) {

                    moveFile(file, videoFolder);
                }

                // Other file types
                else {
                    moveFile(file, otherFolder);
                }
            }
        }

        // STEP 7: Completion message
        System.out.println("File organization completed successfully.");
    }

    // STEP 8: Method to move a file to a destination folder
    private static void moveFile(File file, File destinationFolder) {

        // Create new file path inside destination folder
        File newFile = new File(destinationFolder, file.getName());

        // renameTo() moves the file to new location
        // It returns true if operation is successful
        boolean moved = file.renameTo(newFile);

        // Optional safety check
        if (!moved) {
            System.out.println("Failed to move file: " + file.getName());
        }
    }
}
