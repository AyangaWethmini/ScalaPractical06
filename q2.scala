import scala.io.StdIn.readLine

object StudentRecordApp extends App {
  
  def getStudentInfo: (String, Int, Int, Double, Char) = {
    val name = readLine("Enter student name: ")
    val marks = readLine("Enter obtained marks: ").toInt
    val totalMarks = readLine("Enter total possible marks: ").toInt

    val (isValid, errorMessage) = validateInput(name, marks, totalMarks)

    if (!isValid) {
      throw new IllegalArgumentException(errorMessage.getOrElse("Invalid input"))
    }

    val percentage = (marks.toDouble / totalMarks) * 100
    val grade = percentage match {
      case p if p >= 90 => 'A'
      case p if p >= 75 => 'B'
      case p if p >= 50 => 'C'
      case _ => 'D'
    }

    (name, marks, totalMarks, percentage, grade)
  }

  def printStudentRecord(record: (String, Int, Int, Double, Char)): Unit = {
    val (name, marks, totalMarks, percentage, grade) = record
    println(s"Student Name: $name")
    println(s"Obtained Marks: $marks")
    println(s"Total Marks: $totalMarks")
    println(f"Percentage: $percentage%.2f%%")
    println(s"Grade: $grade")
  }

  def validateInput(name: String, marks: Int, totalMarks: Int): (Boolean, Option[String]) = {
    if (name.trim.isEmpty) {
      (false, Some("Name cannot be empty"))
    } else if (marks < 0 || marks > totalMarks) {
      (false, Some("Marks should be a positive integer not exceeding total possible marks"))
    } else if (totalMarks <= 0) {
      (false, Some("Total possible marks should be a positive integer"))
    } else {
      (true, None)
    }
  }

  def getStudentInfoWithRetry: (String, Int, Int, Double, Char) = {
    var studentRecord: Option[(String, Int, Int, Double, Char)] = None
    
    while (studentRecord.isEmpty) {
      try {
        studentRecord = Some(getStudentInfo)
      } catch {
        case e: IllegalArgumentException =>
          println(e.getMessage)
      }
    }
    
    studentRecord.get
  }

  // Main application entry point
  val studentRecord = getStudentInfoWithRetry
  printStudentRecord(studentRecord)
}
