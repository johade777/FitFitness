package com.example.fitfitness.room

import android.content.Context
import androidx.room.*
import com.example.fitfitness.data.*
import com.example.fitfitness.room.dao.AttemptDao
import com.example.fitfitness.room.dao.ExerciseDao
import com.example.fitfitness.room.dao.RoutineDao
import com.example.fitfitness.room.dao.WorkoutDao
import com.example.fitfitness.room.dao.relationshipDao.ExerciseAttemptDao
import com.example.fitfitness.room.dao.relationshipDao.RoutineWorkoutsDao
import com.example.fitfitness.room.dao.relationshipDao.WorkoutExercisesDao

@Database(entities = [Attempt::class, Exercise::class, Workout::class, Routine::class, WorkoutSession::class, RoutineSession::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class FitDatabase : RoomDatabase() {

    abstract fun routineDao(): RoutineDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun attemptDao(): AttemptDao
    abstract fun exerciseAttemptDao(): ExerciseAttemptDao
    abstract fun workoutExercisesDao(): WorkoutExercisesDao
    abstract fun routineWorkoutDao(): RoutineWorkoutsDao

    companion object {
        private const val DB_NAME = "db_fitapp"
        private var instance: FitDatabase? = null

        fun getInstance(context: Context): FitDatabase? {
            if (instance == null) {
                synchronized(FitDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, FitDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}