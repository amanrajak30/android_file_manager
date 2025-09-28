

package com.amaze.filemanager.utils

import java.io.File

/**
 * For non Google Play variant, this class does nothing. Just a stub.
 */
object PackageInstallValidation {
    /**
     * Do nothing.
     */
    @JvmStatic
    @Throws(PackageCannotBeInstalledException::class, IllegalStateException::class)
    fun validatePackageInstallability(f: File) = Unit

    /**
     * Exception indicating specified package cannot be installed
     */
    class PackageCannotBeInstalledException : Exception {
        constructor(reason: Throwable) : super(reason)
        constructor(reason: String) : super(reason)
    }
}
