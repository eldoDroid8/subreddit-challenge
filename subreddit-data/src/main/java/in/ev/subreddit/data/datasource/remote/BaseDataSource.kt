package `in`.ev.subreddit.data.datasource.remote

import `in`.ev.data.model.EntityResultWrapper
import `in`.ev.data.model.EntityResultWrapper.*
import `in`.ev.data.model.ErrorEntity
import com.squareup.moshi.JsonAdapter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.SocketException

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
abstract class BaseDataSource constructor(
    val retrofit: Retrofit, private val moshiErrorAdapter:
    JsonAdapter<ErrorEntity>
) {
    protected suspend fun <T : Any> getResponse(
        request: suspend () -> Response<T>
    ): EntityResultWrapper<T> {
        return try {
            val result = request.invoke()
            return if (result.isSuccessful) {
                if (null == result.body()) {
                    Error(ErrorEntity(status_message = "Empty response", throwable = Throwable()))
                } else {
                    Success(result.body())
                }
            } else {
                val errorResponse: ErrorEntity = parseError(result)
                Error(errorResponse)
            }
        } catch (e: IOException) {
            Error(ErrorEntity(status_message = "Unknown error", throwable = e))
        } catch (e: SocketException) {
            Error(ErrorEntity(status_message = "Please check your network connection", throwable
            = e))
        } catch (e: HttpException) {
            val errorResponse = convertErrorBody(e)
            Error(errorResponse)
        }
    }

    private fun parseError(response: Response<*>): ErrorEntity {
        val converter = retrofit.responseBodyConverter<ErrorEntity>(
            ErrorEntity::class.java, arrayOfNulls
                (0)
        )
        return try {
            val errorEntity = converter.convert(response.errorBody()) ?: ErrorEntity(
                status_message = "Unknown " + "error", throwable = Throwable())
            errorEntity

        } catch (e: IOException) {
            ErrorEntity(throwable = e)
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorEntity {
        return try {
            val response = moshiErrorAdapter.fromJson(throwable.response()?.errorBody()?.source())
            response ?: ErrorEntity(status_message = "Unknown Error",throwable = throwable)
        } catch (exception: Exception) {
            val errorEntity = ErrorEntity(status_message = "Unknown error",throwable = throwable)
            errorEntity
        }
    }

}