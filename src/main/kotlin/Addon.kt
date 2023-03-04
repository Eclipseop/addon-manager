import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.util.zip.ZipInputStream

data class Addon(var name: String, var url: String) {

  private val bytes by lazy { download() }

  private fun download(): ByteArray {
    println("Downloading ${name}...")
    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()

    val call = client.newCall(request)
    val response = call.execute()

    println("Finished downloading ${name}!")
    return response.body.bytes()
  }

  fun writeToTemp() {
    val tempLocation = Constants.TEMP_LOCATION
    File("${tempLocation}\\${name}.zip").writeBytes(bytes)
    println("Saving ${name} to disk")
  }

  fun unzipFromTemp() {
    println("Extracting ${name}...")
    val fis = FileInputStream("${Constants.TEMP_LOCATION}\\${name}.zip")
    val destDir = "${Constants.TEMP_LOCATION}\\asdf"

    val buffer = ByteArray(1024)

    fis.use {
      val zis = ZipInputStream(fis)
      zis.use {
        var entry = zis.nextEntry
        while (entry != null) {
          val fileName: String = entry.name
          val newFile = File(destDir + File.separator + fileName)
          if (entry.name.endsWith("/")) {
            newFile.mkdirs()
          } else {
            File(newFile.parent).mkdirs()
            val fos = FileOutputStream(newFile)
            var len: Int
            while (zis.read(buffer).also { len = it } > 0) {
              fos.write(buffer, 0, len)
            }
            fos.close()
          }
          zis.closeEntry()
          entry = zis.nextEntry
        }
      }
    }
    println("Done extracting ${name}!")
  }

  fun getHash(): String {
    val hash = MessageDigest.getInstance("MD5").digest(bytes)
    return BigInteger(1, hash).toString(16)
  }
}
