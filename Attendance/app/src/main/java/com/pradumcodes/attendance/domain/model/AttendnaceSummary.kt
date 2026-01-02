package com.pradumcodes.attendance.domain.model

data class AttendanceSummary(
    val delivered: Int,
    val attended: Int,
    val totalExpected: Int
) {
    val percentage: Float
        get() =
            if (delivered == 0) 0f
            else (attended * 100f) / delivered

    val canMissMore: Int
        get() {
            val minRequired = 0.75f
            var miss = 0

            while (true) {
                val futureDelivered = delivered + miss
                val futureAttended = attended

                val percent =
                    (futureAttended * 100f) / futureDelivered

                if (percent < minRequired * 100) break
                miss++
            }
            return miss - 1
        }
}
