package com.example.jsonfeed.util.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseSchedulerProvider {
    abstract fun io(): Scheduler
    abstract fun computation(): Scheduler
    abstract fun ui(): Scheduler
}

class SchedulerProvider : BaseSchedulerProvider() {
    override fun computation() = Schedulers.computation()
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}

class TrampolineSchedulerProvider : BaseSchedulerProvider() {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}