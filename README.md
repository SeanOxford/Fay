# Sean Oxford - Fay Demo


# Code Philosophy

I try to emphasize simplicity and readability over most things, and stay away from heavy convenience abstractions, such as generic base classes, in favor of individual implementations and clear separation of concerns. In all my years of Android development, typing code has never been a bottleneck, it's been trying to implement unpredicted features in rigid abstractions.

# Architecture:

This is largely inspired by Robert Martin's Clean Architecture but allows for flexibility in its approach. Basically, the app's core is the Domain layer which is pure Kotlin, no libraries, pure business logic. Then, through dependency inversion, it communicates with the data and UI layers, which contain the libraries doing mechanical database insertions, network calls, view drawing, etc, to choreograph the app's processes.

## Benefits:
* Scalability - Allows for clear separation of concerns to build in any direction with zero entanglement. View layer just shows what it is given and reports input. Data layer just does CRUD.
* Dependency Interchangeability - Isolation of dependencies makes swapping between libraries a breeze.
* Testability - The Domain layer being pure Kotlin abstractions of its surrounding libraries allows for incredibly simple testability and mocking.
* Faster build times - The modularized nature of this architecture means less code needs to be recompiled for every code change.

# Decisions

## XML vs Compose

I think Compose is great, I love declarative programming and adopt it whole-heartedly in my approaches, but I feel Compose still has some idiosyncrasies to work out to be the best decision for the best product. [Some companies](https://www.youtube.com/watch?v=6lBBpWX1x8Y) have posted horror stories about adopting it, but I'm more than open to switching either immediately if requested or when it becomes a bit more mature. My architectural approach entails the view layer only receive a set of primitives/enums necessary to draw the screen, so switching would be incredibly easy.

## Hilt vs Dagger2

Hilt is also a great library, but I feel like it was built to soften the harsh learning curve that Dagger2 was inflicting on the development community. However, if all devs are familiar with Dagger2 on a project, I think its granular control is more suitable for a large scale product.

## Ktor Vs Retrofit

Love Retrofit, used it for years, but I'm a huge fan of Ktor. It's a super lightweight and mechanical networking library built by the creators of Kotlin and heavily integrated into the language itself


# Notes

* Totally scuffed the transition from login to the appointments screen last minute, that'd be the first thing I'd polish up with more time






