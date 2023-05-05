package services

import play.api.libs.json.Json


case class person(firstName: String, lastName: String, dob: dob)
case class dob(day: Int, month: Int, year: Int)

class BirthDayServices {

  def isTodayYourBirthDay(p: person) = {
    val curDate = java.time.LocalDate.now.toString.split("-")
    val day = curDate(2).toInt
    val month = curDate(1).toInt

    try{
      require(p.dob.day.equals(day) & p.dob.month.equals(month))
      Json.obj("msg" -> "Today is your Birthday")
    } catch {
      case e: Throwable => Json.obj("msg" -> "Today is not your Birthday", "error code" -> e.toString)
    }
  }

}
