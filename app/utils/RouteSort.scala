package utils

object RouteSort {
  
  def getSortedRoutes(routes: Seq[models.Route], sort: String) = sort match {
    case "name" => routes.sortBy(_.getName)
    case "averageRating" => routes.sortBy(_.getAverageRating).reverse
    case _ => routes
  }
  
}