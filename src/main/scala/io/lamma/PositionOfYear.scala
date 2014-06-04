package io.lamma

import io.lamma.Month.{December, January}

/**
 * for each PositionOfYear implementation
 * Lamma expect there is one and only one day match the criteria in each year
 */
trait PositionOfYear {

  /**
   * @return true if the input date is valid to the currently defined position of year
   */
  def isValidDOY(d: Date): Boolean
}

object PositionOfYear {

  def validate(poy: PositionOfYear) = {
    def validate(yyyy: Int) = {
      val result = (Date(yyyy, 1, 1) to Date(yyyy, 12, 31)).filter(poy.isValidDOY).toList
      if (result.size != 1) {
        throw new InvalidPositionOfYearException(poy, yyyy, result)
      }
    }

    validate(1900)  // special non-leap year
    validate(2000)  // leap year
    validate(2014)  // non-leap year
  }

  val FirstDayOfYear = NthDayOfYear(1)

  val SecondDayOfYear = NthDayOfYear(2)

  case class NthDayOfYear(n: Int) extends PositionOfYear {
    require(n > 0 && n <= 366, "Day of year is valid from 1 to 366")

    override def isValidDOY(d: Date) = {
      if (n > d.maxDayOfYear) {
        d.isLastDayOfYear
      } else {
        d.dayOfYear == n
      }
    }
  }

  case object LastDayOfYear extends PositionOfYear {
    override def isValidDOY(d: Date) = d.isLastDayOfYear
  }

  def FirstWeekDayOfYear(weekday: Weekday) = NthWeekdayOfYear(1, weekday)

  case class NthWeekdayOfYear(n: Int, weekday: Weekday) extends PositionOfYear {
    require(n > 0 && n <= 53, "Weekday of year is valid from 1 to 53")
    
    override def isValidDOY(d: Date) = {
      if (d.weekday == weekday) {
        if (d.sameWeekdaysOfYear.size < n) {
          d.sameWeekdaysOfYear.last == d
        } else {
          d.sameWeekdaysOfYear(n - 1) == d
        }
      } else {
        false
      }
    }
  }

  def LastWeekdayOfYear(weekday: Weekday) = NthWeekdayOfYear(53, weekday)

  def FirstMonthOfYear(pom: PositionOfMonth) = NthMonthOfYear(January, pom)

  case class NthMonthOfYear(m: Month, pom: PositionOfMonth) extends PositionOfYear {
    override def isValidDOY(d: Date) = d.month == m && pom.isValidDOM(d)
  }

  def LastMonthOfYear(pom: PositionOfMonth) = NthMonthOfYear(December, pom)
}

class InvalidPositionOfYearException(poy: PositionOfYear, failingYear: Int, result: List[Date])
  extends RuntimeException(s"${poy.toString} does not work for year $failingYear. Please make sure there is one and only one day matches for each year. Actual result: $result")